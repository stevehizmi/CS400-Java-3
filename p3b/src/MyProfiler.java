/**
 * Filename:   MyProfiler.java
 * Project:    p3b-201901     
 * Authors:    Steven Hizmi Lecture 001
 *
 * Semester:   Spring 2019
 * Course:     CS400
 * 
 * Due Date:   Thursday March 28th 10pm
 * Version:    1.0
 * 
 * Credits:    TODO: name individuals and sources outside of course staff
 * 
 * Bugs:       TODO: add any known bugs, or unsolved problems here
 */

// Used as the data structure to test our hash table against
import java.util.*;

public class MyProfiler<K extends Comparable<K>, V> {

	HashTableADT<K, V> hashtable;
	TreeMap<K, V> treemap;

	public MyProfiler() {
		hashtable = new HashTable<K, V>();
		treemap = new TreeMap<K, V>();
		// TODO: complete the Profile constructor
		// Instantiate your HashTable and Java's TreeMap
	}

	public void insert(K key, V value) throws DuplicateKeyException, IllegalNullKeyException {
		hashtable.insert(key, value);
		treemap.put(key, value);
		// TODO: complete insert method
		// Insert K, V into both data structures
	}

	public void retrieve(K key) throws KeyNotFoundException, IllegalNullKeyException {
		hashtable.get(key);
		treemap.get(key);
		// TODO: complete the retrieve method
		// get value V for key K from data structures
	}

	public static void main(String[] args) {
		try {
			Scanner input = new Scanner(System.in);
			System.out.println("Enter number:");
			int numElements = input.nextInt();
			//int numElements = Integer.parseInt(args[0]);

			MyProfiler<Integer, Integer> profile = new MyProfiler<Integer, Integer>();

			for (int i = 0; i < numElements; i++) {
				profile.insert(i, i );
			}

			for (int j = 0; j < numElements; j++) {
				profile.retrieve(j);
			}

			String msg = String.format("Inserted and retreived %d (key,value) pairs", numElements);
			System.out.println(msg);
		} catch (Exception e) {
			System.out.println("Usage: java MyProfiler <number_of_elements>");
			System.exit(1);
		}
	}
}
