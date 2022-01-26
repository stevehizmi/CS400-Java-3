import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.Assertions;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class GraphTest {

	Graph graph;

	@Before
	public void setUp() throws Exception {

	}

	// TODO: add code that runs after each test method
	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void test000_graph_size() {
		graph = new Graph();
		graph.addEdge("A", "B");
		graph.addEdge("B", "C");
		graph.addEdge("D", "E");
		graph.addEdge("E", "F");

		if (graph.size() != 4) {
			fail("size function does not work");
		}
	}

	@Test
	public void test001_graph_order() {
		graph = new Graph();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");

		if (graph.order() != 4) {
			fail("order function does not work");
		}
	}

	@Test
	public void test002_graph_getAllVertices() {
		graph = new Graph();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");

		Set<String> expected = new HashSet<>();
		expected.add("A");
		expected.add("B");
		expected.add("C");
		expected.add("D");

		if (!graph.getAllVertices().equals(expected)) {
			fail("getAllVertices function does not work");
		}
	}

	@Test
	public void test003_graph_getAdjacentVertices() {
		graph = new Graph();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("A", "E");
		graph.addEdge("A", "F");

		List<String> expected = new ArrayList<String>();
		expected.add("B");
		expected.add("C");
		expected.add("E");
		expected.add("F");

		if (!graph.getAdjacentVerticesOf("A").equals(expected)) {
			fail("getAdjacentVertices function does not work");
		}
	}

	@Test
	public void test004_graph_removeEdge() {
		graph = new Graph();
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.addEdge("A", "E");
		graph.addEdge("A", "F");
		graph.removeEdge("A", "F");

		List<String> expected = new ArrayList<String>();
		expected.add("B");
		expected.add("C");
		expected.add("E");

		if (!graph.getAdjacentVerticesOf("A").equals(expected)) {
			fail("removeEdge function does not work");
		}
	}

	@Test
	public void test005_graph_addEdge() {
		graph = new Graph();
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");

		List<String> expected = new ArrayList<String>();
		expected.add("B");
		expected.add("C");

		if (!graph.getAdjacentVerticesOf("A").equals(expected)) {
			fail("addEdge function does not work");
		}
	}

	@Test
	public void test006_graph_removeVertex() {
		graph = new Graph();
		graph.addEdge("A", "B");
		graph.addEdge("A", "C");
		graph.removeVertex("C");

		List<String> expected = new ArrayList<String>();
		expected.add("B");

		if (!graph.getAdjacentVerticesOf("A").equals(expected)) {
			fail("removeVertex function does not work");
		}
	}

	@Test
	public void test007_graph_addVertex() {
		graph = new Graph();
		graph.addVertex("A");
		Set<String> expected = new HashSet<>();
		expected.add("A");

		if (!graph.getAllVertices().equals(expected)) {
			fail("addVertex function does not work");
		}
	}

}