//
// Used chained bucket of linked lists
//
//
// HashTable class implements an array of linked-lists, with the head of each list as the element in the indices
//
public class HashTable<K extends Comparable<K>, V> implements HashTableADT<K, V> {
	private int capacity;
	private double loadFactorThreshold;
	private int numKeys;
	Node<K, V>[] hashTable;

	/*
	 * private inner class Node implements linked-list
	 * 
	 */
	private class Node<L, W> {
		private L key;
		private W value;
		private Node<L, W> next;

		public Node(L key, W value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}

	/*
	 * default hashTable constructor
	 */
	public HashTable() {
		this.capacity = 11;
		this.loadFactorThreshold = 0.75;
		this.numKeys = 0;
	}

	/*
	 * alternate constructor for HashTable class
	 * 
	 * @param initialCapacity, length of array
	 * 
	 * @param loadFactorThreshold
	 */
	public HashTable(int initialCapacity, double loadFactorThreshold) {
		this.numKeys = 0; // initialize numKeys to 0
		this.capacity = initialCapacity; // set capacity to parameter initialCapacity
		this.loadFactorThreshold = loadFactorThreshold; // set loadFactorThreshold to parameter loadFactorThreshold
		hashTable = (Node<K, V>[]) new Node[initialCapacity]; // create hashTable array
	}

	/*
	 * insert node into hashTable
	 * 
	 * @param K key, key value of node to insert
	 * 
	 * @param V value, value of node to insert
	 * 
	 */
	@Override
	public void insert(K key, V value) throws IllegalNullKeyException, DuplicateKeyException {

		// if key is null, throw exception
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		Node<K, V> insertThis = new Node<K, V>(key, value); // create new node insertThis with parameter key and value
		int index = Math.abs(key.hashCode()) % capacity; // set index equal to absolute value of hashCode of key mod
															// capacity

		// if element at index is null, insert new node there
		if (hashTable[index] == null) {
			hashTable[index] = insertThis;
			numKeys++; // increment numKeys
		} else {

			Node<K, V> currNode = hashTable[index]; // set currNode to node at index if index is full

			// if currNode key is equal to key of insertThis, throw exception
			if (currNode.key.equals(key)) {
				throw new DuplicateKeyException();
			}

			// while currNode.next is not null, traverse through list
			while (currNode.next != null) {

				// if currNode key equals insertThis key, throw exception
				if (currNode.key.equals(key)) {
					throw new DuplicateKeyException();
				}
				currNode = currNode.next; // traverse through linked list
			}

			// if currNode key is equal to insertThis key, throw exception
			if (currNode.key.equals(key)) {
				throw new DuplicateKeyException();
			} else {
				currNode.next = insertThis; // set currNode.next to insertThis
				numKeys++; // increment numKeys
			}
		}
		// if loadFactor is greater than or equal to loadFactorThreshold, rehash
		// hashTable
		if (this.getLoadFactor() >= this.loadFactorThreshold) {
			rehash();
		}
	}

	/*
	 * @param K key, key to search for and remove from hashTable
	 *
	 * @return boolean, true if removal is successful, false otherwise
	 */
	@Override
	public boolean remove(K key) throws IllegalNullKeyException {

		// if key is null, throw exception
		if (key == null) {
			throw new IllegalNullKeyException();
		} else {
			int index = Math.abs(key.hashCode()) % capacity; // find index using hashCode of key mod capacity

			Node<K, V> prev = hashTable[index]; // create node prev to element at index of hashTable

			// if element at index is null, return false
			if (hashTable[index] == null) {
				return false;
			}

			// if element at index of hashTable, traverse through hashTable
			if (hashTable[index] != null) {

				while (prev.next != null) {

					// if key is found, remove node and set prev to prev.next.next and decrement
					// numKeys and return true
					if (prev.key.equals(key)) {
						prev.next = prev.next.next;
						numKeys--;
						return true;
					}
					prev = prev.next; // otherwise traverse through hashTable
				}

				// if key is found, set element at index of hashTable to prev.next, decrement
				// numKeys and return true
				if (prev.key.equals(key)) {
					hashTable[index] = prev.next;
					numKeys--;
					return true;
				}
			}
			return false; // otherwise return false
		}
	}

	/*
	 * method get(K key) returns value of parameter key
	 * 
	 * @param K key, key to search for in hashTable
	 * 
	 * @return V, return value of key
	 */
	@Override
	public V get(K key) throws IllegalNullKeyException, KeyNotFoundException {

		// if key is null, throw exception
		if (key == null) {
			throw new IllegalNullKeyException();
		}

		int index = Math.abs(key.hashCode()) % capacity; // calculate index of key using absolute value of hashCode of
															// key, mod capacity

		Node<K, V> currNode = hashTable[index]; // create currNode to element at index

		// if element at index is null, throw exception
		if (hashTable[index] == null) {
			throw new KeyNotFoundException();
		} else {
			// run while currNode.next is not null
			while (currNode.next != null) {

				// if key is found, return currNode value
				if (currNode.key.equals(key)) {
					return currNode.value;
				}

				// otherwise traverse through the list
				currNode = currNode.next;
			}

			// if key is found, return currNode value
			if (currNode.key.equals(key)) {
				return currNode.value;

			}
			// otherwise throw an exception
			else {
				throw new KeyNotFoundException();
			}
		}
	}

	/*
	 * @return number of keys in hashTable
	 */
	@Override
	public int numKeys() {
		return this.numKeys;
	}

	/*
	 * @return loadFactorThreshold of hashTable
	 */
	@Override
	public double getLoadFactorThreshold() {
		return this.loadFactorThreshold;
	}

	/*
	 * @return loadFactor of hashTable
	 */
	@Override
	public double getLoadFactor() {
		return (double) numKeys / (double) capacity;
	}

	/*
	 * @return capacity of hashTable
	 */
	@Override
	public int getCapacity() {
		return this.capacity;
	}

	/*
	 * @return collision resolution I chose
	 */
	@Override
	public int getCollisionResolution() {
		return 5;
	}

	/*
	 * private helper method to resize hashTable once loadFactorThreshold is met
	 * 
	 */
	private void rehash() throws IllegalNullKeyException, DuplicateKeyException {

		Node<K, V> oldTable[] = this.hashTable; // create reference to original table to maintain access to the data in
												// hashTable

		this.capacity = (this.capacity * 2) + 1; // set capacity to double plus 1 to use as the new hashTable's capacity

		this.hashTable = new Node[this.capacity]; // set this hashTable's length to the new capacity value

		Node<K, V> currNode = null; // create empty node

		numKeys = 0; // re-set numKeys to 0

		// traverse through array
		for (int i = 0; i < oldTable.length; i++) {

			currNode = oldTable[i];// set currNode to first index of hashTable array

			// if currNode is not null, traverse through array
			if (currNode != null) {

				// if currNode.next is not null, traverse through linked-list at index i
				while (currNode.next != null) {

					this.insert(currNode.key, currNode.value); // insert node from oldTable to the new table with the
																// corrected capacity

					currNode = currNode.next; // go to the next node to insert that into new hashTable
				}

				this.insert(currNode.key, currNode.value); // insert tail node into hashTable
			}
		}
	}
}
