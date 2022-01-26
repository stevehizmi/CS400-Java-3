import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Filename: PackageManager.java Project: p4 Authors: Steven Hizmi
 * 
 * PackageManager is used to process json package dependency files and provide
 * function that make that information available to other users.
 * 
 * Each package that depends upon other packages has its own entry in the json
 * file.
 * 
 * Package dependencies are important when building software, as you must
 * install packages in an order such that each package is installed after all of
 * the packages that it depends on have been installed.
 * 
 * For example: package A depends upon package B, then package B must be
 * installed before package A.
 * 
 * This program will read package information and provide information about the
 * packages that must be installed before any given package can be installed.
 * all of the packages in
 * 
 * You may add a main method, but we will test all methods with our own Test
 * classes.
 */

public class PackageManager {

	private Graph graph;

	/*
	 * Package Manager default no-argument constructor.
	 */
	public PackageManager() {
		graph = new Graph();
	}

	/**
	 * Takes in a file path for a json file and builds the package dependency graph
	 * from it.
	 *
	 * @param jsonFilepath
	 *            the name of json data file with package dependency information
	 * @throws FileNotFoundException
	 *             if file path is incorrect
	 * @throws IOException
	 *             if the give file cannot be read
	 * @throws ParseException
	 *             if the given json cannot be parsed
	 */
	public void constructGraph(String jsonFilepath) throws FileNotFoundException, IOException, ParseException {
		Package[] pack = null;
		// gets the json file

		if (jsonFilepath == null) {
			throw new FileNotFoundException();
		}
		Object obj = new JSONParser().parse(new FileReader(jsonFilepath));
		JSONObject jo = (JSONObject) obj;

		// creates an array of "packages" from the JSON file
		JSONArray packages = (JSONArray) jo.get("packages");

		// converts the json packages to java packages
		pack = new Package[packages.size()];
		for (int i = 0; i < packages.size(); i++) {

			// get the package
			JSONObject jsonPackage = (JSONObject) packages.get(i);

			// gets the anme of the current package
			String packageName = (String) jsonPackage.get("name");

			// gets the list of dependencies of the current package
			JSONArray dependencies = (JSONArray) jsonPackage.get("dependencies");

			// coverts dependencies to a string array
			String[] dep = new String[dependencies.size()];
			for (int j = 0; j < dep.length; j++) {
				dep[j] = (String) dependencies.get(j);
			}
			// creates new package from JSON package and stores in an array
			Package currentPackage = new Package(packageName, dep);
			pack[i] = currentPackage;
		}
		// Add vertices and edges
		for (int i = 0; i < pack.length; i++) {
			graph.addVertex(pack[i].getName());

			for (int j = 0; j < pack[i].getDependencies().length; j++)
				graph.addEdge(pack[i].getName(), pack[i].getDependencies()[j]);
		}

	}

	/**
	 * Helper method to get all packages in the graph.
	 * 
	 * @return Set<String> of all the packages
	 */
	public Set<String> getAllPackages() {
		return graph.getAllVertices();
	}

	/**
	 * Given a package name, returns a list of packages in a valid installation
	 * order.
	 *
	 * Valid installation order means that each package is listed before any
	 * packages that depend upon that package.
	 *
	 * @return List<String>, order in which the packages have to be installed
	 *
	 * @throws CycleException
	 *             if you encounter a cycle in the graph while finding the
	 *             installation order for a particular package. Tip: Cycles in some
	 *             other part of the graph that do not affect the installation order
	 *             for the specified package, should not throw this exception.
	 *
	 * @throws PackageNotFoundException
	 *             if the package passed does not exist in the dependency graph.
	 */
	public List<String> getInstallationOrder(String pkg) throws CycleException, PackageNotFoundException {

		// gets all of the packages
		Set<String> allPackages = graph.getAllVertices();

		if (!allPackages.contains(pkg)) // checks to make sure the pkg exists
			throw new PackageNotFoundException();
		LinkedList<String> visited = new LinkedList<String>();

		// runs recursive helper method to get the list
		this.installationHelper(visited, pkg, new LinkedList<String>());

		visited.add(pkg); // adds the original package

		return visited;

	}

	/*
	 * gets all of the dependencies of curr and adds them to visited in a valid
	 * order
	 *
	 * Valid installation order means that each package is listed before any
	 * packages that depend upon that package.
	 *
	 * @throws CycleException if you encounter a cycle in the graph while finding
	 * the installation order for a particular package. Tip: Cycles in some other
	 * part of the graph that do not affect the installation order for the specified
	 * package, should not throw this exception.
	 */
	private void installationHelper(LinkedList<String> visited, String curr, LinkedList<String> previous)
			throws CycleException {

		// this will be the level most recently added
		LinkedList<String> justAdded = new LinkedList<String>();

		// iterates through each one edge away dependency of current
		for (String v : graph.getAdjacentVerticesOf(curr))

			// if v has not been visited add it to the list
			if (!visited.contains(v)) {
				visited.add(0, v);
				justAdded.add(v);

				// if not in previous level and if already visited, then a cycle occurred
			} else if (!previous.contains(v))
				throw new CycleException();
			else {

				// if visited, but in the previous level, then reordering occurs
				int index = visited.indexOf(v);
				visited.remove(index);
				visited.add(0, v);
			}

		// recurses for each just added node
		for (String v : justAdded)
			this.installationHelper(visited, v, justAdded);
	}

	/**
	 * Given two packages - one to be installed and the other installed, return a
	 * List of the packages that need to be newly installed.
	 *
	 * For example, refer to shared_dependecies.json - toInstall("A","B") If package
	 * A needs to be installed and packageB is already installed, return the list
	 * ["A", "C"] since D will have been installed when B was previously installed.
	 *
	 * @return List<String>, packages that need to be newly installed.
	 *
	 * @throws CycleException
	 *             if you encounter a cycle in the graph while finding the
	 *             dependencies of the given packages. If there is a cycle in some
	 *             other part of the graph that doesn't affect the parsing of these
	 *             dependencies, cycle exception should not be thrown.
	 *
	 * @throws PackageNotFoundException
	 *             if any of the packages passed do not exist in the dependency
	 *             graph.
	 */
	public List<String> toInstall(String newPkg, String installedPkg) throws CycleException, PackageNotFoundException {

		// get list of already installed packages
		List<String> installed = this.getInstallationOrder(installedPkg);

		// get list of packages to install
		List<String> toInstall = this.getInstallationOrder(newPkg);

		// if installed contains the new package
		if (!installed.contains(newPkg)) {

			// removes all of the packages from toInstall that are also in installed
			for (String vertex : installed)
				toInstall.remove(vertex);
			return toInstall;
		}

		// if installed does not contain the new packages
		if (installed.contains(newPkg)) {
			for (String vertex : toInstall)

				// removes any package from installed that is also in toInstall
				installed.remove(vertex);
			installed.add(0, newPkg);
			return installed;
		} else
			return toInstall;
	}

	/**
	 * Return a valid global installation order of all the packages in the
	 * dependency graph.
	 *
	 * assumes: no package has been installed and you are required to install all
	 * the packages
	 *
	 * returns a valid installation order that will not violate any dependencies
	 *
	 * @return List<String>, order in which all the packages have to be installed
	 * @throws CycleException
	 *             if you encounter a cycle in the graph
	 */
	public List<String> getInstallationOrderForAllPackages() throws CycleException {

		// finds package with max dependencies
		String maxDependencies = this.getPackageWithMaxDependencies();
		Set<String> allVertices = graph.getAllVertices();
		allVertices.remove(maxDependencies);
		List<String> order = new LinkedList<String>();
		try {
			// sets the order of installation to the installation order of maxDependencies
			order = this.getInstallationOrder(maxDependencies);

			// goes through all vertices in the Graph and adds any package not in the list
			// into the correct spot
			for (String v : allVertices) {

				// this is the installation order of the current package
				List<String> subOrder = this.getInstallationOrder(v);
				if (!order.contains(v)) // only modifies order if v is not in the list
					// goes through each package in the current nodes installation order
					// and adds it to the list
					for (String pkg : subOrder)
						// only adds the package if it is not in the list
						if (!order.contains(pkg))
							order.add(pkg);
			}
			System.out.println(order);
		} catch (PackageNotFoundException e) {

			/* should never happen */}
		return order;
	}

	/**
	 * Find and return the name of the package with the maximum number of
	 * dependencies.
	 *
	 * Tip: it's not just the number of dependencies given in the json file. The
	 * number of dependencies includes the dependencies of its dependencies. But, if
	 * a package is listed in multiple places, it is only counted once.
	 *
	 * Example: if A depends on B and C, and B depends on C, and C depends on D.
	 * Then, A has 3 dependencies - B,C and D.
	 *
	 * @return String, name of the package with most dependencies.
	 * @throws CycleException
	 *             if you encounter a cycle in the graph
	 */
	public String getPackageWithMaxDependencies() throws CycleException {

		// gets all packages
		Set<String> packages = this.getAllPackages();

		// this will represent the longest path in the graph
		List<String> longest = new LinkedList();
		List<String> temp;
		String maxDependencies = "";

		// goes through each package and compares all paths for each packages to the
		// previous longest
		for (String pkg : packages) {
			try {
				temp = this.getInstallationOrder(pkg);
				if (temp.size() > longest.size()) { // compares path sizes
					longest = temp;
					maxDependencies = pkg;
				}
			} catch (PackageNotFoundException e) {
				/* do nothing */}
		}
		return maxDependencies;
	}

	public static void main(String[] args)
			throws FileNotFoundException, IOException, ParseException, CycleException, PackageNotFoundException {

	}
}
