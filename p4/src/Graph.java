import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Filename: Graph.java Project: p4 Authors:
 * 
 * Directed and unweighted graph implementation
 */

public class Graph implements GraphADT {

	private ArrayList<Vertex> vertices;

	private class Vertex {
		private String name;
		private ArrayList<Vertex> successor;

		private Vertex(String name) {
			this.name = name;
			successor = new ArrayList<Vertex>();
		}
	}

	/*
	 * Default no-argument constructor
	 */
	public Graph() {
		vertices = new ArrayList<Vertex>(); // initialize an ArrayList of Vertices 
	}

	/**
	 * Add new vertex to the graph.
	 *
	 * If vertex is null or already exists, method ends without adding a vertex or
	 * throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 * 
	 * @param vertex to add to list of vertices
	 */
	public void addVertex(String vertex) {
		
		if (vertex == null) // if vertex is null return
			return;
		
		// 
		for (int i = 0; i < vertices.size(); i++) {
			// if vertex is already in vertices, return
			if (vertices.get(i).name.equals(vertex)) {
				return;
			}
		}
		vertices.add(new Vertex(vertex)); // add vertex to vertices ArrayList
	}

	/**
	 * Remove a vertex and all associated edges from the graph.
	 * 
	 * If vertex is null or does not exist, method ends without removing a vertex,
	 * edges, or throwing an exception.
	 * 
	 * Valid argument conditions: 1. vertex is non-null 2. vertex is not already in
	 * the graph
	 * 
	 * @param vertex to remove from list
	 */
	public void removeVertex(String vertex) {
		Vertex v = null;
		
		// if parameter is null, return
		if (vertex == null)
			return;
		
		// traverse through vertices, if name matches then remove vertex
		for (int i = 0; i < vertices.size(); i++) {
			if (vertices.get(i).name.equals(vertex)) {
				v = vertices.remove(i);
				i--;
				break;
			}
		}
		
		// remove vertex from successors list
		if (v != null) {
			for (Vertex x : vertices) {
				int index = x.successor.indexOf(v);
				if (index != -1)
					x.successor.remove(index);
			}
		}
	}

	/**
	 * Add the edge from vertex1 to vertex2 to this graph. (edge is directed and
	 * unweighted) If either vertex does not exist, no edge is added and no
	 * exception is thrown. If the edge exists in the graph, no edge is added and no
	 * exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge is not in the graph
	 * 
	 * @param vertex1
	 * @param vertex2
	 * 
	 * Add edge from vertex1 to vertex2
	 */
	public void addEdge(String vertex1, String vertex2) {
		Vertex from = null;
		Vertex to = null;

		boolean v1 = false;
		boolean v2 = false;

		// traverse through vertices and find v1 and v2, set bool to false otherwise
		for (int i = 0; i < vertices.size(); i++) {
			String x = vertices.get(i).name;
			if (x.equals(vertex1)) {
				from = vertices.get(i);
				v1 = true;
			}
			if (x.equals(vertex2)) {
				to = vertices.get(i);
				v2 = true;
			}

		}
		// if vertex1 doesn't exist, add it to the list then add the edge
		if (v1 == false) {
			vertices.add(new Vertex(vertex1));
			from = vertices.get(vertices.size() - 1);
		}
		// if vertex2 doesn't exist, add it to the list then add the edge
		if (v2 == false) {
			vertices.add(new Vertex(vertex2));
			to = vertices.get(vertices.size() - 1);
		}

		// if both vertices are not null, add edge between them
		if (from != null && to != null) {
			if (!from.successor.contains(to))
				from.successor.add(to);
		}
		return;
	}

	/**
	 * Remove the edge from vertex1 to vertex2 from this graph. (edge is directed
	 * and unweighted) If either vertex does not exist, or if an edge from vertex1
	 * to vertex2 does not exist, no edge is removed and no exception is thrown.
	 * 
	 * Valid argument conditions: 1. neither vertex is null 2. both vertices are in
	 * the graph 3. the edge from vertex1 to vertex2 is in the graph
	 * 
	 * @param vertex1
	 * @param vertex2
	 * Remove edge from vertex1 to vertex2
	 * 
	 */
	public void removeEdge(String vertex1, String vertex2) {
		
		// if there are less than 2 vertices, return
		if (vertices.size() < 2)
			return;
		
		Vertex from = null;
		Vertex to = null;
		
		// if either parameter is null, return
		if (vertex1 == null || vertex2 == null)
			return;
		
		// find the 2 vertices to remove edge from
		for (int i = 0; i < vertices.size(); i++) {
			String x = vertices.get(i).name;
			if (x.equals(vertex1))
				from = vertices.get(i);
			if (x.equals(vertex2))
				to = vertices.get(i);
		}
		
		// remove edge from vertex1 to vertex2
		if (from != null && to != null) {
			int index = from.successor.indexOf(to);
			if (index != -1)
				from.successor.remove(index);
		}
	}

	/**
	 * Returns a Set that contains all the vertices
	 * 
	 * @return Set of strings
	 * 
	 * 
	 */
	public Set<String> getAllVertices() {
		
		Set<String> stringVertices = new HashSet<String>();
		
		for (Vertex v : vertices) {
			stringVertices.add(v.name);
		}
		return stringVertices;
	}

	/**
	 * Get all the neighbor (adjacent) vertices of a vertex
	 *
	 * @param vertex
	 * @return List of Strings
	 * 
	 */
	public List<String> getAdjacentVerticesOf(String vertex) {
		Vertex v = null;
		
		List<String> adjacencyList = new ArrayList<String>();
		
		// find vertex 
		for (int i = 0; i < vertices.size(); i++)
			if (vertices.get(i).name.equals(vertex)) {
				v = vertices.get(i);
				break;
			}
		
		// add to adjacencyList 
		if (v != null)
			for (Vertex adjecentV : v.successor)
				adjacencyList.add(adjecentV.name);
		
		return adjacencyList;
	}

	/**
	 * Returns the number of edges in this graph.
	 * 
	 * @return int size
	 */
	public int size() {
		int size = 0;
		for (Vertex v : vertices)
			size += v.successor.size();
		return size;
	}

	/**
	 * Returns the number of vertices in this graph.
	 * 
	 * @return int order
	 */
	public int order() {
		return vertices.size();
	}
}
