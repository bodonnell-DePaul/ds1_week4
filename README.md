## What is the difference between different Java Data Structures

### Deque
- **Type**: Double-ended queue
- **Implementation**: Interface, can be implemented using various data structures (e.g., `ArrayDeque`, `LinkedList`)
- **Order**: Maintains the order of insertion
- **Access**: Elements can be added or removed from both ends
- **Duplicates**: Allows duplicate elements
- **Usage**: Suitable for scenarios where elements need to be added or removed from both ends, such as implementing stacks and queues

Example:
```java
import java.util.Deque;
import java.util.LinkedList;

public class DequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("apple");
        deque.addLast("banana");
        deque.addFirst("cherry");
        System.out.println(deque.pollFirst()); // Output: cherry
        System.out.println(deque.pollLast()); // Output: banana
    }
}
```

### ArrayDeque
- **Type**: Double-ended queue
- **Implementation**: Resizable array
- **Order**: Maintains the order of insertion
- **Access**: Elements can be added or removed from both ends
- **Duplicates**: Allows duplicate elements
- **Usage**: Suitable for scenarios where elements need to be added or removed from both ends, and a resizable array implementation is preferred

Example:
```java
import java.util.ArrayDeque;
import java.util.Deque;

public class ArrayDequeExample {
    public static void main(String[] args) {
        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst("apple");
        deque.addLast("banana");
        deque.addFirst("cherry");
        System.out.println(deque.pollFirst()); // Output: cherry
        System.out.println(deque.pollLast()); // Output: banana
    }
}
```

### HashMap
- **Type**: Map
- **Implementation**: Hash table
- **Order**: Does not maintain any order (from Java 8 onwards, insertion order is maintained in some cases)
- **Access**: Elements are accessed by their key
- **Duplicates**: Keys must be unique, but values can be duplicated
- **Usage**: Suitable for storing key-value pairs where quick lookup by key is needed

Example:
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("apple", 1);
map.put("banana", 2);
map.put("apple", 3); // The value for key "apple" is updated
System.out.println(map.get("apple")); // Output: 3
```

### HashSet
- **Type**: Set
- **Implementation**: Hash table
- **Order**: Does not maintain any order (from Java 8 onwards, insertion order is maintained in some cases)
- **Access**: Elements are accessed via iteration
- **Duplicates**: Does not allow duplicate elements
- **Usage**: Suitable for storing unique elements and provides constant-time performance for basic operations like add, remove, and contains

Example:
```java
HashSet<String> set = new HashSet<>();
set.add("apple");
set.add("banana");
set.add("apple"); // Duplicate, will not be added
System.out.println(set.contains("banana")); // Output: true
System.out.println(set); // Output: [banana, apple] (order may vary)
```

# Custom HashSet and HashMap Implementations in Java

## 1. Custom HashSet Implementation

A HashSet is a collection that uses hash codes to store unique elements in a way that allows for very fast retrieval.

---
### HashSet
- **Type**: Set
- **Implementation**: Hash table
- **Order**: Does not maintain any order (from Java 8 onwards, insertion order is maintained in some cases)
- **Access**: Elements are accessed via iteration
- **Duplicates**: Does not allow duplicate elements
- **Usage**: Suitable for storing unique elements and provides constant-time performance for basic operations like add, remove, and contains

Example:
```java
HashSet<String> set = new HashSet<>();
set.add("apple");
set.add("banana");
set.add("apple"); // Duplicate, will not be added
System.out.println(set.contains("banana")); // Output: true
System.out.println(set); // Output: [banana, apple] (order may vary)
---

    ````java
    public class CustomHashSet<E> {
        private static final int DEFAULT_CAPACITY = 16;
        private static final float DEFAULT_LOAD_FACTOR = 0.75f;
        
        private Object[] buckets;
        private int size;
        private float loadFactor;
        
        // Node class for chaining to resolve collisions
        private static class Node<E> {
            private E element;
            private Node<E> next;
            
            public Node(E element) {
                this.element = element;
                this.next = null;
            }
        }
        
        public CustomHashSet() {
            this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
        }
        
        public CustomHashSet(int initialCapacity) {
            this(initialCapacity, DEFAULT_LOAD_FACTOR);
        }
        
        public CustomHashSet(int initialCapacity, float loadFactor) {
            if (initialCapacity <= 0) {
                throw new IllegalArgumentException("Initial capacity must be positive");
            }
            if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
                throw new IllegalArgumentException("Load factor must be positive");
            }
            
            this.buckets = new Object[initialCapacity];
            this.size = 0;
            this.loadFactor = loadFactor;
        }
        
        // Add an element to the set
        public boolean add(E element) {
            if (element == null) {
                throw new NullPointerException("HashSet does not allow null elements");
            }
            
            // Check if we need to resize
            if (size >= buckets.length * loadFactor) {
                resize(buckets.length * 2);
            }
            
            int index = getBucketIndex(element);
            
            // Check if element already exists
            @SuppressWarnings("unchecked")
            Node<E> current = (Node<E>) buckets[index];
            while (current != null) {
                if (current.element.equals(element)) {
                    return false; // Element already exists
                }
                current = current.next;
            }
            
            // Add new element at the beginning of the linked list for this bucket
            @SuppressWarnings("unchecked")
            Node<E> head = (Node<E>) buckets[index];
            Node<E> newNode = new Node<>(element);
            newNode.next = head;
            buckets[index] = newNode;
            
            size++;
            return true;
        }
        
        // Remove an element from the set
        public boolean remove(Object element) {
            if (element == null) {
                return false;
            }
            
            int index = getBucketIndex(element);
            
            @SuppressWarnings("unchecked")
            Node<E> current = (Node<E>) buckets[index];
            Node<E> prev = null;
            
            while (current != null) {
                if (current.element.equals(element)) {
                    // Element found, remove it
                    if (prev == null) {
                        buckets[index] = current.next;
                    } else {
                        prev.next = current.next;
                    }
                    size--;
                    return true;
                }
                prev = current;
                current = current.next;
            }
            
            return false; // Element not found
        }
        
        // Check if an element exists in the set
        public boolean contains(Object element) {
            if (element == null) {
                return false;
            }
            
            int index = getBucketIndex(element);
            
            @SuppressWarnings("unchecked")
            Node<E> current = (Node<E>) buckets[index];
            
            while (current != null) {
                if (current.element.equals(element)) {
                    return true;
                }
                current = current.next;
            }
            
            return false;
        }
        
        // Get the bucket index for an element
        private int getBucketIndex(Object element) {
            int hashCode = element.hashCode();
            // Handle negative hash codes
            hashCode = hashCode < 0 ? -hashCode : hashCode;
            return hashCode % buckets.length;
        }
        
        // Resize the buckets array
        private void resize(int newCapacity) {
            Object[] oldBuckets = buckets;
            buckets = new Object[newCapacity];
            
            // Rehash all elements into the new buckets
            for (int i = 0; i < oldBuckets.length; i++) {
                @SuppressWarnings("unchecked")
                Node<E> current = (Node<E>) oldBuckets[i];
                
                while (current != null) {
                    Node<E> next = current.next;
                    
                    // Recompute index for the element
                    int newIndex = getBucketIndex(current.element);
                    
                    // Add to the beginning of the linked list in the new bucket
                    @SuppressWarnings("unchecked")
                    Node<E> head = (Node<E>) buckets[newIndex];
                    current.next = head;
                    buckets[newIndex] = current;
                    
                    current = next;
                }
            }
        }
        
        // Get the number of elements in the set
        public int size() {
            return size;
        }
        
        // Check if the set is empty
        public boolean isEmpty() {
            return size == 0;
        }
        
        // Clear the set
        public void clear() {
            buckets = new Object[DEFAULT_CAPACITY];
            size = 0;
        }
    }
    ````

 ## 2. Custom HashMap Implementation

A HashMap uses key-value pairs and hash codes for efficient storage and retrieval.

---
### HashMap
- **Type**: Map
- **Implementation**: Hash table
- **Order**: Does not maintain any order (from Java 8 onwards, insertion order is maintained in some cases)
- **Access**: Elements are accessed by their key
- **Duplicates**: Keys must be unique, but values can be duplicated
- **Usage**: Suitable for storing key-value pairs where quick lookup by key is needed

Example:
```java
HashMap<String, Integer> map = new HashMap<>();
map.put("apple", 1);
map.put("banana", 2);
map.put("apple", 3); // The value for key "apple" is updated
System.out.println(map.get("apple")); // Output: 3
```
---

````java
public class CustomHashMap<K, V> {
    private static final int DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    
    private Entry<K, V>[] buckets;
    private int size;
    private float loadFactor;
    
    // Entry class for storing key-value pairs
    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry<K, V> next;
        
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }
    
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        this.buckets = new Entry[DEFAULT_CAPACITY];
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    
    @SuppressWarnings("unchecked")
    public CustomHashMap(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        this.buckets = new Entry[initialCapacity];
        this.size = 0;
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    
    public CustomHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive");
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Load factor must be positive");
        }
        
        @SuppressWarnings("unchecked")
        Entry<K, V>[] newBuckets = new Entry[initialCapacity];
        this.buckets = newBuckets;
        this.size = 0;
        this.loadFactor = loadFactor;
    }
    
    // Put a key-value pair in the map
    public V put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("HashMap does not allow null keys");
        }
        
        // Check if we need to resize
        if (size >= buckets.length * loadFactor) {
            resize(buckets.length * 2);
        }
        
        int index = getBucketIndex(key);
        
        // Check if key already exists
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                V oldValue = current.value;
                current.value = value; // Update value
                return oldValue;
            }
            current = current.next;
        }
        
        // Add new entry at the beginning of the linked list for this bucket
        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.next = buckets[index];
        buckets[index] = newEntry;
        
        size++;
        return null;
    }
    
    // Get a value by key
    public V get(Object key) {
        if (key == null) {
            return null;
        }
        
        int index = getBucketIndex(key);
        
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return current.value;
            }
            current = current.next;
        }
        
        return null; // Key not found
    }
    
    // Remove a key-value pair from the map
    public V remove(Object key) {
        if (key == null) {
            return null;
        }
        
        int index = getBucketIndex(key);
        
        Entry<K, V> current = buckets[index];
        Entry<K, V> prev = null;
        
        while (current != null) {
            if (current.key.equals(key)) {
                // Key found, remove the entry
                if (prev == null) {
                    buckets[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return current.value;
            }
            prev = current;
            current = current.next;
        }
        
        return null; // Key not found
    }
    
    // Check if the map contains a key
    public boolean containsKey(Object key) {
        if (key == null) {
            return false;
        }
        
        int index = getBucketIndex(key);
        
        Entry<K, V> current = buckets[index];
        while (current != null) {
            if (current.key.equals(key)) {
                return true;
            }
            current = current.next;
        }
        
        return false;
    }
    
    // Get the bucket index for a key
    private int getBucketIndex(Object key) {
        int hashCode = key.hashCode();
        // Handle negative hash codes
        hashCode = hashCode < 0 ? -hashCode : hashCode;
        return hashCode % buckets.length;
    }
    
    // Resize the buckets array
    @SuppressWarnings("unchecked")
    private void resize(int newCapacity) {
        Entry<K, V>[] oldBuckets = buckets;
        Entry<K, V>[] newBuckets = new Entry[newCapacity];
        buckets = newBuckets;
        
        // Rehash all entries into the new buckets
        for (int i = 0; i < oldBuckets.length; i++) {
            Entry<K, V> current = oldBuckets[i];
            
            while (current != null) {
                Entry<K, V> next = current.next;
                
                // Recompute index for the entry
                int newIndex = getBucketIndex(current.key);
                
                // Add to the beginning of the linked list in the new bucket
                current.next = newBuckets[newIndex];
                newBuckets[newIndex] = current;
                
                current = next;
            }
        }
    }
    
    // Get the number of key-value pairs in the map
    public int size() {
        return size;
    }
    
    // Check if the map is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    // Clear the map
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }
}
````

## Key Concepts for HashSet and HashMap

1. **Hashing**: Both implementations use the `hashCode()` method to determine where to store elements.

2. **Collision Resolution**: We've implemented chaining (using linked lists) to handle hash collisions.

3. **Load Factor**: Controls when to resize the underlying array to maintain efficiency.

4. **Time Complexity**:
   - Average case: O(1) for add, remove, contains/get operations
   - Worst case: O(n) if many elements hash to the same bucket

5. **Resizing**: When the number of elements exceeds the load factor threshold, the underlying array is resized to maintain performance.

6. **Differences between HashSet and HashMap**:
   - HashSet stores single elements with no duplicates
   - HashMap stores key-value pairs with unique keys

7. **Implementation Challenge**: Proper handling of hash collisions is critical for maintaining good performance.


### Summary
- **ArrayList**: Ordered collection, allows duplicates, accessed by index.
- **LinkedList**: Slower random access, fast insertions/deletions.
- **Stack**: Ordered collection, follows Last-In-First-Out (LIFO) principle, accessed via push 
and pop operations.
- **Queue**: Ordered collection, follows First-In-First-Out (FIFO) principle, accessed via add and remove operations.
- **PriorityQueue**: Ordered by priority, elements accessed based on priority.
- **Deque**: Double-ended queue, elements can be added/removed from both ends.
- **ArrayDeque**: Double-ended queue implemented using a resizable array, elements can be added/removed from both ends.
- **HashMap**: Key-value pairs, keys are unique, accessed by key.
- **HashSet**: Unordered collection, unique elements, accessed via iteration.


### Summary
- **Deque**: Double-ended queue, elements can be added/removed from both ends.
- **ArrayDeque**: Double-ended queue implemented using a resizable array, elements can be added/removed from both ends.
- **HashMap**: Key-value pairs, keys are unique, accessed by key.
- **HashSet**: Unordered collection, unique elements, accessed via iteration.

```java


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Deque;
import java.util.ArrayDeque;

public class App {
    public static void main(String[] args) {
        BeingsInMyHouse BriansCircus = new BeingsInMyHouse();
        BriansCircus.addAnimal("Golden Retriever Walnut");
        BriansCircus.addAnimal("Golden Retriever Cashew");
        BriansCircus.addPerson("Brian");
        BriansCircus.addPerson("Brian's Wife");
        BriansCircus.addPerson("Brian's Daughter");
        BriansCircus.addPerson("Brian's Son");
        BriansCircus.addPerson("Brian's Daughter's Friend");

        // ArrayList
        ArrayList<String> list = new ArrayList<String>();
        list.add("Hello");
        list.add("World");
        list.add("Java");
        list.add("Programming");
        list.add("Language");
        list.add("Data Structures");

        // LinkedList
        LinkedList<String> linkedList = new LinkedList<String>();
        linkedList.add("Hello");
        linkedList.add("World");
        linkedList.add("Java");
        linkedList.add("Programming");
        linkedList.add("Language");

        // Stack
        Stack<String> stack = new Stack<>();
        stack.push("apple");
        stack.push("banana");
        stack.push("cherry");
        System.out.println(stack.peek()); // Output: cherry
        System.out.println(stack.pop()); // Output: cherry
        System.out.println(stack.pop()); // Output: banana
        System.out.println(stack.isEmpty()); // Output: false

        // PriorityQueue
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(5);
        pq.add(1);
        pq.add(3);
        System.out.println(pq.poll()); // Output: 1 (smallest element)
        System.out.println(pq.poll()); // Output: 3
        System.out.println(pq.poll()); // Output: 5

        // Deque
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("apple");
        deque.addLast("banana");
        deque.addFirst("cherry");
        System.out.println(deque.pollFirst()); // Output: cherry
        System.out.println(deque.pollLast()); // Output: banana

        // ArrayDeque
        Deque<String> arrayDeque = new ArrayDeque<>();
        arrayDeque.addFirst("apple");
        arrayDeque.addLast("banana");
        arrayDeque.addFirst("cherry");
        System.out.println(arrayDeque.pollFirst()); // Output: cherry
        System.out.println(arrayDeque.pollLast()); // Output: banana

        // HashMap
        HashMap<String, Integer> map = new HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        map.put("apple", 3); // The value for key "apple" is updated
        System.out.println(map.get("apple")); // Output: 3

        // HashSet
        HashSet<String> set = new HashSet<>();
        set.add("apple");
        set.add("banana");
        set.add("apple"); // Duplicate, will not be added
        System.out.println(set.contains("banana")); // Output: true
        System.out.println(set); // Output: [banana, apple] (order may vary)

        System.out.println("Hello World!");
    }
}
```
