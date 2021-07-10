
// --== CS400 File Header Information ==--
// Name: <your full name>
// Email: <yliu2232@wisc.edu email address>
// Notes to Grader: <optional extra notes>
import java.util.NoSuchElementException;
import java.util.LinkedList;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{
	int capacity;
	int size;
	private LinkedList<Node<KeyType ,ValueType>>[] hashTable;
	
	// constrcutor;
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		System.out.println("parameter constructing capacity =" + capacity);
		// initialize linked list
		hashTable = (LinkedList<Node<KeyType ,ValueType>>[]) new LinkedList<?>[capacity];
		for (int i =0; i<capacity; i++) {
			hashTable[i] = new LinkedList<Node<KeyType ,ValueType>>();
		}
	}
	// constructor:
	public HashTableMap() {
		this.capacity = 10; // with default capacity = 10
		System.out.println("no parameter constructing capacity =" + capacity);
		// initialize linked list
		hashTable = (LinkedList<Node<KeyType ,ValueType>>[]) new LinkedList<?>[capacity];
		for (int i =0; i<capacity; i++) {
			hashTable[i] = new LinkedList<Node<KeyType ,ValueType>>();
		}
	}
	
	// compute the hashCode for key
	private int computeHash(KeyType key) {
		int hash_index;
		// index is the (abs of key's hashCode) mod capacity
		hash_index = Math.abs(key.hashCode()) % capacity; 		
		return hash_index;
	}
	
	// put function
	public boolean put(KeyType key, ValueType value) {
		System.out.println("Add new node:");
		if(key == null) {
			System.out.println("Key is null.");
			return false;
		}
		int hash_index = computeHash(key); // compute the hashCode
		System.out.println("Hash_index is " + hash_index); // print the hashCode
		System.out.println("Hash_table_capacity:" + hashTable.length);
		System.out.println("Is the slot empty? " + hashTable[hash_index].isEmpty());
		Node<KeyType, ValueType> toAdd = new Node(key, value);
		// if it is empty then add a node
		if (hashTable[hash_index].isEmpty()) {
			hashTable[hash_index].add(toAdd);
			size ++;
		}
		else {
			// if it is not empty then loop through it
			boolean same_node = false;
			for (int len = hashTable[hash_index].size(), i = 0; i < len; i ++) {
				Node<KeyType ,ValueType> tmp = hashTable[hash_index].get(i); // get the node
				if(tmp.key.equals(key)) {
					System.out.println("Same key");
					same_node = true;
					return false;
				}
			}
			if(!same_node) {
				hashTable[hash_index].add(toAdd);
				System.out.println("Add in the end");
				size ++;	
			}
		}
		if ((1.0 * size) / capacity > 0.85) {
			System.out.println("Capacity is nearly full, doubling capacity");
			LinkedList<Node<KeyType, ValueType>>[] temp = hashTable;
			// re-initialize
			// LinkedList<Node<KeyType ,ValueType>>[] hashTable;
			capacity = 2 * capacity; 
			hashTable = new LinkedList[capacity];
			// hashTable = (LinkedList<Node<KeyType ,ValueType>>[]) new LinkedList<?>[capacity];
			for (int i = 0; i<capacity; i++) {
				hashTable[i] = new LinkedList<Node<KeyType ,ValueType>>();
			}
			// 
			int len = temp.length; // raw array length
			for (int i = 0; i<len; i++) {
				for (int len2 = temp[i].size(), j = 0; j < len2; j ++) {
					// rehasing
					int new_index = computeHash(temp[i].get(j).key);
					System.out.println("For key:" + temp[i].get(j).key);
					System.out.println("New hash:" + new_index);
					hashTable[new_index].add(temp[i].get(j));
				}
			}
		}
		return true;
		// extension
		//if ((1.0 * size) / capacity > 0.85) {
		//	LinkedList<Node<KeyType ,ValueType>>[] hashTable
		//}
		// hashTable[hash] = new LinkedList<ValueType>(); // initalize a linkedlist
		// hashTable[hash].add(value); // add the value
	}
	
	// get function
	public ValueType get(KeyType key) throws NoSuchElementException {
		if(key == null) {
			System.out.println("Key is null.");
			return null;
		}
		
		int hash_index = computeHash(key); // compute the hashCode
		for (Node<KeyType ,ValueType> i : hashTable[hash_index]) {
			if (i.key.equals(key)) {
				return i.value;
			}
		}
		return null;
		}
	
	// size function
	public int size() {
		return size;
	}
	
	// contains function
	public boolean containsKey(KeyType key) {
		int hash_index = computeHash(key);
		for (Node<KeyType ,ValueType> i : hashTable[hash_index]) {
			if (i.key.equals(key)) {
				System.out.println("Key existed: "+ i.key);
				return true;
			}else {
				return false;
			}
		}
		return false;
	}

		
	
	// Remove function
	public ValueType remove(KeyType key) {
		int hash_index = computeHash(key); // compute the hashCode
		// if it is empty return 0
		if (hashTable[hash_index].isEmpty()) {
			System.out.println("The list is empty.");
			return null;
			}	
		else {
		for (Node<KeyType ,ValueType> i : hashTable[hash_index]) {
			if (i.key.equals(key)) {
				hashTable[hash_index].remove(i); // delete this element
				System.out.println("Element deleted: "+ i.key);
				size--;
				return i.value;
			}
		}
	}
		
		return null; // dont exist
	}
	
	// clear function
	public void clear() {
		for (int i=0; i < hashTable.length;i++) {
			hashTable[i].clear(); // clear linkedlist
		}
		size = 0;
		System.out.println("Hashtable cleared.");
	}
	
	
}

// A node class
class Node<K, V> {
	K key;
	V value;
	
	public Node(K key, V value) {
		this.key = key;
		this.value = value;
	}
}