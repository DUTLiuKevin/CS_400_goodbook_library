// --== CS400 File Header Information ==--
// Name: <Yuan Liu>
// Email: <yliu2232@wisc.edu>
// Notes to Grader: <optional extra notes>

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.io.Serializable;

@SuppressWarnings("serial")
public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>, Serializable{
	int capacity;
	int size;
	private LinkedList<Node<KeyType ,ValueType>>[] hashTable;
	
	// constrcutor;
	@SuppressWarnings("unchecked")
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		// initialize linked list
		hashTable = (LinkedList<Node<KeyType ,ValueType>>[]) new LinkedList<?>[capacity];
		for (int i = 0; i<capacity; i++) {
			hashTable[i] = new LinkedList<Node<KeyType ,ValueType>>();
		}
	}
	// constructor:
	@SuppressWarnings("unchecked")
	public HashTableMap() {
		this.capacity = 10; // with default capacity = 10
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
	
	// clear function
	public void clear() 
	{
		for (int i=0; i < hashTable.length;i++) 
		{
			hashTable[i].clear(); // clear the linkedlist
		}
		size = 0;
		System.out.println("Hashtable cleared.");
	}
	
	// size function
	public int size() 
	{
		return size;
	}
	
	// values function
	public List<Node<KeyType ,ValueType>> values(){
		List<Node<KeyType, ValueType>> values = new ArrayList<Node<KeyType, ValueType>>();
		// loop through array
		for (LinkedList<Node<KeyType ,ValueType>> list:hashTable ) {
			// loop through list
			for (Node<KeyType ,ValueType> i : list) {
				values.add(i);
			}
		}
		return values;
	}
	
	// keys function
	public List<KeyType> keys(){
		List<KeyType> keys = new ArrayList<KeyType>();
		// loop through array
		for (LinkedList<Node<KeyType ,ValueType>> list:hashTable ) {
				keys.add(list.get(0).key);
		}
		return keys;
	}
	
	// contains function
	public boolean containsKey(KeyType key) 
	{
		int hash_index = computeHash(key);
		// iterate through the linked list
		for (Node<KeyType ,ValueType> i : hashTable[hash_index]) 
		{
			if (i.key.equals(key)) 
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		return false;
	}
	
	// put function
	@SuppressWarnings("unchecked")
	public boolean put(KeyType key, ValueType value) 
	{
		// System.out.println("Add new node:");
		if(key == null) 
		{
			return false;
		}
		int hash_index = computeHash(key); // compute the hashCode
		@SuppressWarnings("rawtypes")
		Node<KeyType, ValueType> toAdd = new Node(key, value);
		// if it is empty, add a node
		if (hashTable[hash_index].isEmpty()) 
		{
			hashTable[hash_index].add(toAdd);
			size ++;
		}
		else 
		{
			// if not empty iterate this linkedlist
			boolean same_node = false;
			for (int len = hashTable[hash_index].size(), i = 0; i < len; i ++) 
			{
				Node<KeyType ,ValueType> tmp = hashTable[hash_index].get(i); // get the node
				if(tmp.key.equals(key)) 
				{
					same_node = true; // if there is an existing key, return false
					return false;
				}
			}
			if(!same_node) {
				hashTable[hash_index].add(toAdd); // if there is not an existing key, add in the end
				size ++;	
			}
		}
		// Grow by doubling capacity and rehashing
		if ((1.0 * size) / capacity >= 0.85) 
		{
			LinkedList<Node<KeyType, ValueType>>[] temp = hashTable;
			// re-initialize
			capacity = 2 * capacity; 
			@SuppressWarnings("rawtypes")
			LinkedList<Node<KeyType, ValueType>>[] hashTable = (LinkedList<Node<KeyType, ValueType>>[]) new LinkedList[capacity];
			for (int i = 0; i<capacity; i++) 
			{
				hashTable[i] = new LinkedList<Node<KeyType ,ValueType>>();
			}
			// iterate to rehashing 
			int len = temp.length; // raw array length
			for (int i = 0; i<len; i++) 
			{
				for (int len2 = temp[i].size(), j = 0; j < len2; j ++) 
				{
					// rehashing
					int new_index = computeHash(temp[i].get(j).key);
					hashTable[new_index].add(temp[i].get(j));
				}
			}
		}
		return true;
	}
	
	// get function
	public ValueType get(KeyType key) throws NoSuchElementException 
	{
		if(key == null) 
		{
			return null;
		}
		int hash_index = computeHash(key); // compute the hashCode
		for (Node<KeyType ,ValueType> i : hashTable[hash_index]) 
		{
			if (i.key.equals(key)) {
				return i.value;
			}
		}
		throw new NoSuchElementException();
		}	
	
	// Remove function
	public ValueType remove(KeyType key) 
	{
		int hash_index = computeHash(key); // compute the hashCode
		// if it is empty, return null
		if (hashTable[hash_index].isEmpty()) 
		{
			return null;
			}	
		else {
		for (Node<KeyType ,ValueType> i : hashTable[hash_index]) 
		{
			if (i.key.equals(key)) 
			{
				hashTable[hash_index].remove(i); // remove 
				size--;
				return i.value;
			}
		}
	}
		return null;
	}
}

// A node class
//class Node<K, V> implements Serializable
//{
//	K key;
//	V value;
//	
//	public Node(K key, V value) 
//	{
//		this.key = key;
//		this.value = value;
//	}
//}