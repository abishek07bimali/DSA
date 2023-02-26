import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;


//Design and Implement LFU caching
class Question4A {

    // Map to store the key-value pairs
    private Map<Integer, Node> valueMap = new HashMap<>();

    // Map to store the count (frequency) of each key
    private Map<Integer, Integer> countMap = new HashMap<>();

    // Map to store the linked lists of keys with the same count (frequency)
    private TreeMap<Integer, DoubleLinkedList> frequencyMap = new TreeMap<>();

    // The maximum capacity of the cache
    private final int size;

    // Constructor to set the maximum capacity of the cache
    public Question4A(int n) {
        size = n;
    }

    // Get the value for the given key from the cache
    public int get(int key) {
        // If the key is not in the cache or the cache has no capacity, return -1
        if (!valueMap.containsKey(key) || size == 0) {
            return -1;
        }

        // Remove the key from its current frequency list and move it to the next one in O(1) time
        Node nodeToDelete = valueMap.get(key);
        Node node = new Node(key, nodeToDelete.value());
        int frequency = countMap.get(key);
        frequencyMap.get(frequency).remove(nodeToDelete);
        removeIfListEmpty(frequency);
        valueMap.remove(key);
        countMap.remove(key);
        valueMap.put(key, node);
        countMap.put(key, frequency + 1);
        frequencyMap.computeIfAbsent(frequency + 1, k -> new DoubleLinkedList()).add(node);
        return valueMap.get(key).value;
    }

    // Add or update the value for the given key in the cache
    public void put(int key, int value) {
        // If the key is not in the cache and the cache has capacity
        if (!valueMap.containsKey(key) && size > 0) {
            // Create a new node for the key-value pair
            Node node = new Node(key, value);

            // If the cache is full, remove the LRU node from the smallest frequency list
            if (valueMap.size() == size) {
                int lowestCount = frequencyMap.firstKey();
                Node nodeToDelete = frequencyMap.get(lowestCount).head();
                frequencyMap.get(lowestCount).remove(nodeToDelete);
                removeIfListEmpty(lowestCount);
                int keyToDelete = nodeToDelete.key();
                valueMap.remove(keyToDelete);
                countMap.remove(keyToDelete);
            }

            // Add the node to the frequency list for frequency 1 and update the maps
            frequencyMap.computeIfAbsent(1, k -> new DoubleLinkedList()).add(node);
            valueMap.put(key, node);
            countMap.put(key, 1);
        }
        // If the key is already in the cache
        else if (size > 0) {
            // Create a new node for the key-value pair
            Node node = new Node(key, value);

            // Remove the key from its current frequency list and move it to the next one in O(1) time
            Node nodeToDelete = valueMap.get(key);
            int frequency = countMap.get(key);
            frequencyMap.get(frequency).remove(nodeToDelete);
            removeIfListEmpty(frequency);
            valueMap.remove(key);
            countMap.remove(key);

            // Add the node to the frequency list for the next frequency and update the maps
            valueMap.put(key, node);
            countMap.put(key, frequency + 1);
           frequencyMap.computeIfAbsent(frequency + 1, k -> new DoubleLinkedList()).add(node);


       }
   }

   private void removeIfListEmpty(int frequency) {
       // remove from map if list is empty
       if (frequencyMap.get(frequency).len() == 0) {
           frequencyMap.remove(frequency);
       }
   }

   private class Node {
       private int key;
       private int value;
       Node next;
       Node prev;

       public Node(int key, int value) {
           this.key = key;
           this.value = value;
       }

       public int key() {
           return key;
       }

       public int value() {
           return value;
       }
   }

   private class DoubleLinkedList {
       private int n;
       private Node head;
       private Node tail;

       public void add(Node node) {
           if (head == null) {
               head = node;
           } else {
               tail.next = node;
               node.prev = tail;
           }
           tail = node;
           n++;
       }

       public void remove(Node node) {

           if (node.next == null) tail = node.prev;
           else node.next.prev = node.prev;

           if (node.prev == null) head = node.next;
           else node.prev.next = node.next;

           n--;
       }

       public Node head() {
           return head;
       }

       public int len() {
           return n;
       }
   }
    public static void main(String[] args) {
        Question4A cache = new Question4A(2); // create a cache with capacity of 2

        // add elements to cache
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));    // returns 1
        cache.put(3, 3);                     // evicts key 2
        System.out.println(cache.get(2));    // returns -1 (not found)
        System.out.println(cache.get(3));    // returns 3
        cache.put(4, 4);                     // evicts key 1
        System.out.println(cache.get(1));    // returns -1 (not found)
        System.out.println(cache.get(3));    // returns 3
        System.out.println(cache.get(4));    // returns 4
    }

}