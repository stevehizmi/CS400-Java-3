import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class DataStructureADTTest<T extends DataStructureADT<String, String>> {

	private T dataStructureInstance;

	protected abstract T createInstance();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		dataStructureInstance = createInstance();
	}

	@AfterEach
	void tearDown() throws Exception {
		dataStructureInstance = null;
	}

	@Test
	void test00_empty_ds_size() {
		if (dataStructureInstance.size() != 0)
			fail("data structure should be empty, with size=0, but size=" + dataStructureInstance.size());
	}

	// TODO: implement tests 01 - 04

	@Test
	public void test01_after_insert_one_size_is_one() {
		dataStructureInstance.insert("231093", "4");
		if (dataStructureInstance.size() != 1) {
			fail("size is not equal to 1 after using insert method");
		}

	}

	@Test
	public void test02_after_insert_one_remove_one_size_is_0() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.remove("4");
		if (dataStructureInstance.size() != 0) {
			fail("size is not equal to 0 after using insert method followed by remove method");
		}
	}

	@Test
	public void test03_duplicate_exception_is_thrown() {
		boolean thrown = false;
		try {
			dataStructureInstance.insert("4", "5");
			dataStructureInstance.insert("3", "5");
			dataStructureInstance.insert("2", "5");
			dataStructureInstance.insert("4", "5");
		} catch (RuntimeException e) {
			thrown = true;
		}
		if (thrown == false) {
			fail("duplicate exception is not thrown");
		}

	}

	@Test
	public void test04_remove_returns_false_when_key_not_present() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.insert("2", "5");
		dataStructureInstance.insert("3", "5");
		if (dataStructureInstance.remove("7") != false) {
			fail("method does not return false when key is not present");

		}

	}

	@Test
	public void test05_insert_remove() {
		dataStructureInstance.insert("4", "5");
		if (dataStructureInstance.remove("4") == false) {
			fail("remove method does not return true ");
		}

	}

	@Test
	public void test06_insert_return_size() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.insert("2", "5");
		dataStructureInstance.insert("3", "5");
		if (dataStructureInstance.size() != 3) {
			fail("size is not equal to 3");
		}

	}

	@Test
	public void test07_contains_method() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.insert("2", "5");
		dataStructureInstance.insert("3", "5");
		if (dataStructureInstance.contains("4") != true) {
			fail("contains method wrong");
		}
	}

	@Test
	public void test08_insert_many_check_size() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.insert("2", "5");
		dataStructureInstance.insert("3", "5");
		dataStructureInstance.insert("10", "5");
		dataStructureInstance.insert("15", "5");
		dataStructureInstance.insert("90", "5");
		dataStructureInstance.insert("john", "5");
		dataStructureInstance.insert("28", "5");
		dataStructureInstance.insert("1293", "5");
		if (dataStructureInstance.size() != 9) {
			fail("size expected: 9");
		}
	}

	@Test
	public void test09_insert_remove_insert() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.remove("4");
		dataStructureInstance.insert("4", "5");
		if (dataStructureInstance.size() != 1) {
			fail("insert same element after deleting it does not work");
		}
	}

	@Test
	public void test10_duplicate_value_insterted_away() {
		boolean thrown = false;
		try {
			dataStructureInstance.insert("4", "5");
			dataStructureInstance.insert("2", "5");
			dataStructureInstance.insert("3", "5");
			dataStructureInstance.insert("10", "5");
			dataStructureInstance.insert("15", "5");
			dataStructureInstance.insert("90", "5");
			dataStructureInstance.insert("john", "5");
			dataStructureInstance.insert("28", "5");
			dataStructureInstance.insert("4", "5");
		} catch (RuntimeException e) {
			thrown = true;
		}
		if (thrown == false) {
			fail("duplicate key was added");
		}

	}

	@Test
	public void test11_insert_remove_insert_contains() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.remove("4");
		dataStructureInstance.insert("4", "5");
		if (dataStructureInstance.contains("4") != true) {
			fail("Does not contain key after inserting.");
		}

	}

	@Test
	public void test12_insert_remove_remove_insert_getSize() {
		dataStructureInstance.insert("4", "5");
		dataStructureInstance.insert("7", "5");
		dataStructureInstance.remove("4");
		dataStructureInstance.remove("7");
		if (dataStructureInstance.size() != 0) {
			fail("size is not equal to 0 after inserting twice and deleting twice");
		}
	}

	@Test
	public void test13_insert_contains_remove_size() {
		dataStructureInstance.insert("4", "5");
		if (dataStructureInstance.contains("4") != true) {
			fail("list does not contain expected key");
		}
		dataStructureInstance.remove("4");
		if (dataStructureInstance.size() != 0) {
			fail("size expected to be 0");
		}
	}

	@Test
	public void test14_empty_list() {
		if (dataStructureInstance.size() != 0) {
			fail("List is not empty when inserting nothing");
		}
	}

	// TODO: add tests to ensure that you can detect implementation that fail

	// Tip: consider different numbers of inserts and removes and how different
	// combinations of insert and removes

}
