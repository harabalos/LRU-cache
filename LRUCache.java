public class LRUCache<K, V> implements Cache<K, V> {

    private final int capacity; // maximum number of elements the cache can hold
    private HashTable<K, Node> map; // map to store key-value pairs and corresponding nodes
    private Node head; // head of the linked list
    private Node tail; // tail of the linked list
    private long hits; // number of cache hits
    private long misses; // number of cache misses

    // Node class to store key-value pairs and pointers to the next and previous nodes
    private class Node {
        K key;
        V value;
        Node prev;
        Node next;
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashTable<>(); 
        this.head = null; 
        this.tail = null; 
        this.hits = 0; // no hits
        this.misses = 0; // no misses
    }

    public V lookUp(K key) {
        Node node = map.get(key); // get the node corresponding to the key from the map
        if (node == null) {
            misses++; // increment the number of misses
            return null;
        } else {
            hits++; // increment the number of hits
            if (node != head) {
                // If the node is not already at the front of the list, move it to the front
                removeNode(node);
                addToFront(node);
            }
            return node.value; // return the value corresponding to the key
        }
    }

    public void store(K key, V value) {
        Node node = map.get(key); // get the node corresponding to the key from the map
        if (node != null) {
            // If the node already exists, update its value and move it to the front of the list
            node.value = value;
            if (node != head) {
                removeNode(node);
                addToFront(node);
            }
        } else {
            // If the node does not exist, create a new node and add it to the front of the list
            node = new Node();
            node.key = key;
            node.value = value;
            map.put(key, node); // add the key-value pair and corresponding node to the map
            addToFront(node);
            if (map.size() > capacity) {
                // If the size of the map exceeds the capacity, evict the least recently used node
                map.remove(tail.key);
                removeNode(tail);
            }
        }
    }

    public double getHitRatio() {
        long totalLookups = hits + misses; // total number of cache lookups
        if (totalLookups == 0) {
            return 0.0; // if there are no lookups, hit ratio is 0
        } else {
            return (double) hits / totalLookups; // hit ratio is the number of hits divided by total lookups
        }
    }


    public long getHits() {
        return hits; // return the number of hits
    }


    public long getMisses() {
        return misses; // return the number of misses
    }


    public long getNumberOfLookUps() {
        return hits + misses; // return the total number of cache lookups
    }

    private void addToFront(Node node) {
        // Set the next node of the new node to be the current head
        node.next = head;
        // Set the previous node of the new node to be null
        node.prev = null;
        // If the current head is not null, set its previous node to be the new node
        if (head != null) {
            head.prev = node;
        }
        // Set the head of the list to be the new node
        head = node;
        // If the tail is null (i.e. the list was empty), set it to be the new node
        if (tail == null) {
            tail = head;
        }
    }
    

    private void removeNode(Node node) {
        // If the node being removed has a previous node, set its next node to be the node being removed's next node
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            // Otherwise, set the head of the list to be the node being removed's next node
            head = node.next;
        }
        // If the node being removed has a next node, set its previous node to be the node being removed's previous node
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            // Otherwise, set the tail of the list to be the node being removed's previous node
            tail = node.prev;
        }
    }
    
}
