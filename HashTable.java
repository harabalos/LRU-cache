public class HashTable<K, V> {

   //V for values and K for keys
   //array of HashNode objects
   private HashNode<K, V>[] buckets;

   //the number of buckets in the hash table
   private int numOfBuckets;

   //the number of key-value pairs stored in the hash table
   private int size;


   private static class HashNode<K, V> {

       private K key;
       private V value;
       private HashNode<K, V> next;

       public HashNode(K key, V value) {
           this.key = key;
           this.value = value;
       }
   }

   public HashTable() {
       this(10);
   }


   public HashTable(int capacity) {
       this.numOfBuckets = capacity;
       this.buckets = new HashNode[numOfBuckets];
       this.size = 0;
   }

   //returns the number of key-value pairs stored in the hash table.
   public int size() {
       return size;
   }

   //Returns true if the hash table contains no key-value pairs, false otherwise.
   public boolean isEmpty() {
       return size == 0;
   }

   //adds a new key-value pair to the hash table
   public void put(K key, V value) {
      //check if either the key or the value is null
      if (key == null || value == null) {
         throw new IllegalArgumentException("Key or Value is null !!!");
      }
      //get the bucket index for the key
      int bucketIndex = getBucketIndex(key);
      //get the head of the linked list for this bucket
      HashNode<K, V> head = buckets[bucketIndex];
      //traverse the linked list to check if the key already exists
      while (head != null) {
         //if the key already exists, replace the value and return
         if (head.key.equals(key)) {
            head.value = value;
            return;
         }
         head = head.next;
      }
      //if the key does not exist, create a new node and add it to the head of the linked list
      size++;
      head = buckets[bucketIndex];
      HashNode<K, V> node = new HashNode<>(key, value);
      node.next = head;
      buckets[bucketIndex] = node;
   }


   private int getBucketIndex(K key) {
       return key.hashCode() % numOfBuckets; // buckets.length
   }




   public V get(K key) {
      //check if key is null
      if (key == null) {
         throw new IllegalArgumentException("Key is null !!!");
      }
      
      //get the index of the bucket where the key is expected to be found
      int bucketIndex = getBucketIndex(key);
      
      //get the head node of the linked list at the bucket index
      HashNode<K, V> head = buckets[bucketIndex];
      
      //traverse the linked list to find the node with the given key
      while (head != null) {
         //check if the current node has the same key as the given key
         if (head.key.equals(key)) {
            //return the value associated with the key
            return head.value;
         }
         //move to the next node in the linked list
         head = head.next;
      }
   
      //the key was not found in the linked list, return null
      return null;
   }



   public V remove(K key) {
      //ensure that the key is not null
      if (key == null) {
         throw new IllegalArgumentException("Key cannot be null.");
      }

      //calculate the bucket index for the given key
      int bucketIndex = getBucketIndex(key);

      //get the head node for the corresponding bucket
      HashNode<K, V> head = buckets[bucketIndex];
      HashNode<K, V> previous = null;

      //traverse the linked list until the key is found or the end of the list is reached
      while (head != null && !head.key.equals(key)) {
         previous = head;
         head = head.next;
      }

      //if the key is not found in the linked list, return null
      if (head == null) {
         return null;
      }

      //decrement the size of the hash table
      size--;

      //if the node to be removed is not the head node, update the previous node's next reference
      if (previous != null) {
         previous.next = head.next;
      } else { //if the node to be removed is the head node, update the bucket's head reference
         buckets[bucketIndex] = head.next;
      }

      //return the value associated with the removed key-value pair
      return head.value;
   }


}
