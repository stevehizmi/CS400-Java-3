import static org.junit.jupiter.api.Assertions.*; // org.junit.Assert.*; 
import org.junit.jupiter.api.Assertions;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

/*
 * HashTableTest class provides JUnit tests to test my implementation of my HashTable class
 */
public class HashTableTest {

	// // TODO: add other fields that will be used by multiple tests
	//
	// // TODO: add code that runs before each test method
	// @Before
	// public void setUp() throws Exception {
	//
	// }
	//
	// // TODO: add code that runs after each test method
	// @After
	// public void tearDown() throws Exception {
	//
	// }

	/**
	 * Tests that a HashTable returns an integer code indicating which collision
	 * resolution strategy is used. REFER TO HashTableADT for valid collision scheme
	 * codes.
	 */
	@Test
	public void test000_collision_scheme() {
		HashTableADT htIntegerKey = new HashTable<Integer, String>();
		int scheme = htIntegerKey.getCollisionResolution();
		if (scheme < 1 || scheme > 9)
			fail("collision resolution must be indicated with 1-9");
	}

	/**
	 * IMPLEMENTED AS EXAMPLE FOR YOU Tests that insert(null,null) throws
	 * IllegalNullKeyException
	 */
	@Test
	public void test001_IllegalNullKey() {
		try {
			HashTableADT htIntegerKey = new HashTable<Integer, String>();
			htIntegerKey.insert(null, null);
			fail("should not be able to insert null key");
		} catch (IllegalNullKeyException e) {
			/* expected */ } catch (Exception e) {
			fail("insert null key should not throw exception " + e.getClass().getName());
		}
	}

	/*
	 * Test if after inserting one node, that the numKeys field is correctly
	 * incremented
	 */
	@Test
	public void test002_insert() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .50);
			hash.insert(10, "1st value");
			if (hash.numKeys() != 1) {
				fail("get method returns invalid value");
			}
		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("Something else is going on");
		}
	}

	/*
	 * test if numKeys value is correct after inserting nodes and removing nodes
	 */
	@Test
	public void test003_insert_remove() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .50);
			hash.insert(10, "1st value");
			hash.insert(11, "1st value");
			hash.insert(12, "1st value");
			hash.insert(13, "1st value");
			hash.insert(14, "1st value");
			hash.insert(15, "1st value");
			hash.remove(10);
			hash.remove(11);
			hash.remove(14);
			// System.out.println(hash.numKeys());
			if (hash.numKeys() != 3) {
				fail("get method returns invalid value");
			}
		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("Something else is going on");
		}
	}

	/*
	 * test if get method works after inserting multiple pieces of data
	 */
	@Test
	public void test004_insert_many_get_one() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .50);
			hash.insert(10, "1st value");
			hash.insert(11, "2nd value");
			hash.insert(12, "2nd2 value");
			hash.insert(13, "3rd value");
			hash.insert(14, "4th value");
			hash.insert(15, "5th value");

			if (hash.get(15) != "5th value") {
				fail("get method returns invalid value");
			}
		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("Something else is going on");
		}
	}

	/*
	 * tests if after inserting multiple and removing multiple if get method returns
	 * correct value
	 */
	@Test
	public void test005_insert_remove_get() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(2, .7);
			hash.insert(10, "1st value");
			hash.insert(12, "2nd value");
			hash.insert(15, "3rd value");
			hash.remove(10);
			hash.remove(15);

			if (hash.get(12) != "2nd value") {
				fail("insert and remove methods do not work properly");
			}

		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("something else is wrong");
		}
	}

	/*
	 * tests if correct loadFactor is returned after inserting multiple nodes
	 */
	@Test
	public void test006_insert_loadFactor() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .75);
			hash.insert(10, "1st value");
			hash.insert(12, "2nd value");
			hash.insert(15, "3rd value");

			if (hash.getLoadFactor() != 0.3) {
				fail("insert and remove methods do not work properly");
			}

		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("something else is wrong");
		}
	}

	/*
	 * tests if loadFactorThreshold is correct after inserting 3 nodes
	 */
	@Test
	public void test007_insert_getLoadFactorThreshold() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .75);
			hash.insert(10, "1st value");
			hash.insert(12, "2nd value");
			hash.insert(15, "3rd value");

			if (hash.getLoadFactorThreshold() != 0.75) {
				fail("insert and remove methods do not work properly");
			}

		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("something else is wrong");
		}
	}

	/*
	 * tests if numKeys is correct after inserting multiple nodes
	 */
	@Test
	public void test008_insert_numKeys() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .75);
			hash.insert(10, "1st value");
			hash.insert(12, "2nd value");
			hash.insert(15, "3rd value");

			if (hash.numKeys() != 3) {
				fail("insert and remove methods do not work properly");
			}

		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("something else is wrong");
		}
	}

	@Test
	public void test009_insert_capacity() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .75);
			hash.insert(10, "1st value");
			hash.insert(12, "2nd value");
			hash.insert(15, "3rd value");

			if (hash.getCapacity() != 10) {
				fail("insert and remove methods do not work properly");
			}

		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("something else is wrong");
		}
	}

	/*
	 * tests if numKeys is correct after inserting and removing nodes
	 */
	@Test
	public void test010_insert_remove_numKeys() {
		try {
			HashTableADT hash = new HashTable<Integer, String>(10, .75);
			hash.insert(10, "1st value");
			hash.insert(12, "2nd value");
			hash.insert(15, "3rd value");
			hash.remove(10);

			if (hash.numKeys() != 2) {
				fail("insert and remove methods do not work properly");
			}

		} catch (DuplicateKeyException e) {
			fail("dupe exception");
		} catch (IllegalNullKeyException e) {
			fail("illegal exception");
		} catch (Exception e) {
			fail("something else is wrong");
		}
	}

}
