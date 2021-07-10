import java.io.Serializable;

// A node class
@SuppressWarnings("serial")
class Node<K, V> implements Serializable
{
	K key;
	V value;
	
	public Node(K key, V value) 
	{
		this.key = key;
		this.value = value;
	}
}