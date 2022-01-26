public class DS_My<K extends Comparable<K>, V> implements DataStructureADT<K, V> {
	// TODO may wish to define an inner class
	// for storing key and value as a pair
	// such a class and its members should be "private"

	private class Node<S extends Comparable<S>, H> {
		private S key;
		private H value;
		private Node<S, H> next;

		// constructor
		private Node(S key, H value) {
			this.key = key;
			this.value = value;
			this.next = null;
		}
	}

	private Node<K, V> head; // initialize head node
	private int size; // initialize size of list

	// Private Fields of the class
	// TODO create field(s) here to store data pairs
	public DS_My() {
		// TODO Auto-generated method stub
		size = 0; // size set to 0
		head = null; // head set to null because list is empty
	}

	// Add the key,value pair to the data structure and increases size.
	// If key is null, throws IllegalArgumentException("null key");
	// If key is already in data structure, throws RuntimeException("duplicate
	// key");
	// can accept and insert null values

	/*
	 * 
	 * @param K k, k is key value of node
	 * 
	 * @param V v, v is value of specified key
	 * 
	 * Inserts key to the end of the list with specified value
	 */
	@Override
	public void insert(K k, V v) {

		if (k == null) { // if key is null, exception is thrown
			throw new IllegalArgumentException("null key");
		}
		if (this.size == 0) { // if list is empty, parameters key and value are set as head
			head = new Node<K, V>(k, v);
			size++;
		} else {
			Node<K, V> newNode = new Node<K, V>(k, v); // initialize new node with parameter's arguments
			Node<K, V> last = head; // initialize last as counter to keep track
			while (last.next != null) { // run while current nodes next node is not null
				if (last.key.compareTo(k) == 0) { // if keys match, throw exception
					throw new RuntimeException("duplicate key");
				}
				last = last.next; // set last as the next node in the list
			}
			if (last.key.compareTo(k) == 0) { // checks last node for duplicate key, throws exception if so
				throw new RuntimeException("duplicate key");
			}

			size++;
			last.next = newNode; // adds newNode to end of list
		}
	}

	/*
	 * @param K k, specifies which key element to remove.
	 * 
	 * @return true if removal was successful, false otherwise
	 */
	@Override
	public boolean remove(K k) {

		if (k == null) { // if key is null, throw an exception
			throw new IllegalArgumentException("null key");
		}
		if (this.size == 0) { // if list is empty, return false
			return false;
		}
		if (!this.contains(k)) // if there's a duplicate key, return false
			return false;

		if (head.key.compareTo(k) == 0) { // if head.key is equal to k, remove head
			head = head.next;
			this.size--;
			return true;
		}
		Node<K, V> prevNode = head; // initialize prevNode as head, to keep track of prev node
		Node<K, V> currentNode = head.next; // initialize currentNode to compare key values
		for (int i = 0; i < size; i++) {
			if (currentNode.key.compareTo(k) == 0) { // if specified key is found, delete currentNode
				prevNode.next = currentNode.next; // set prevNode.next to currentNode.next which sets currentNode to
													// null
				this.size--; // decrement size
				return true;
			}
			prevNode = prevNode.next; // set prevNode to next subsequent node
			currentNode = currentNode.next; // set currentNode to next subsequent node
		}
		return false; // return false if unsuccessful
	}

	/*
	 * @Param K k, specified key value method searches for
	 * 
	 * @Return true if list contains specified key, false otherwise
	 */
	@Override
	public boolean contains(K k) {

		Node<K, V> currentNode = head; // set current node to head node
		if (k == null || size == 0) { // if specified key value is null or if the list is empty, return false
			return false;
		}
		while (currentNode.next != null) { // while the currentNode.next is not null, run the loop
			if (currentNode.key.compareTo(k) == 0) { // if current key and param key match, return true
				return true;
			}
			currentNode = currentNode.next; // iterate through list, set currentNode to next subsequent node
		}
		if (currentNode.key.compareTo(k) == 0) { // tests last node in list
			return true;
		}
		return false; // returns false if specified key is not found.
	}

	/*
	 * @param K k, specified key method searches for
	 * 
	 * @return V, return value of specified key
	 * 
	 */
	@Override
	public V get(K k) {

		if (k == null) { // if k param is null, throw exception
			throw new IllegalArgumentException("null key");
		}
		Node<K, V> currentNode = head; // sets currentNode to head node
		for (int i = 0; i < size; i++) {
			if (currentNode.key.compareTo(k) == 0) { // if current key matches param key, return current value
				return currentNode.value;
			}
			currentNode = currentNode.next; // iterate through list of nodes
		}
		return null; // return null if specified key value is not found
	}

	/*
	 * @returns int size of list
	 * 
	 */
	@Override
	public int size() {

		return this.size; // returns size of current list
	}
}