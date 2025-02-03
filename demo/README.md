## What is the difference between a Java ArrayList a HashMap and HashSet
In Java, `ArrayList`, `HashMap` and `HashSet` are two different types of collections that serve different purposes:

### ArrayList
- **Type**: List
- **Implementation**: Resizable array
- **Order**: Maintains the order of insertion
- **Access**: Elements can be accessed by their index
- **Duplicates**: Allows duplicate elements
- **Usage**: Suitable for storing a list of items where order matters and quick access by index is needed

Example:
```java
ArrayList<String> list = new ArrayList<>();
list.add("apple");
list.add("banana");
list.add("apple"); // Duplicates are allowed
System.out.println(list.get(1)); // Output: banana
```
### LinkedList
- **Type**: List, Deque
- **Implementation**: Doubly linked list
- **Order**: Maintains the order of insertion
- **Access**: Elements are accessed by iterating from the beginning or end, which takes linear time `O(n)`
- **Insertion/Deletion**: Inserting or deleting elements is efficient, especially at the beginning or end, taking constant time `O(1)`
- **Usage**: Suitable for scenarios where frequent insertions/deletions are needed and access by index is infrequent

Example:
```java
LinkedList<String> list = new LinkedList<>();
list.add("apple");
list.add("banana");
list.add("cherry");
System.out.println(list.get(1)); // Output: banana
```

### Stack
- **Type**: Stack
- **Implementation**: Uses a dynamic array or linked list internally
- **Order**: Follows Last-In-First-Out (LIFO) principle
- **Access**: Elements are accessed by pushing and popping from the top of the stack
- **Duplicates**: Allows duplicate elements
- **Usage**: Suitable for scenarios like expression evaluation, backtracking, function call management, undo mechanisms, and syntax parsing

Example:
```java
Stack<String> stack = new Stack<>();
stack.push("apple");
stack.push("banana");
stack.push("cherry");
System.out.println(stack.peek()); // Output: cherry
System.out.println(stack.pop()); // Output: cherry
System.out.println(stack.pop()); // Output: banana
System.out.println(stack.isEmpty()); // Output: false
```

### Queue
- **Type**: Queue
- **Implementation**: Uses a linked list or array internally
- **Order**: Follows First-In-First-Out (FIFO) principle
- **Access**: Elements are accessed by adding to the end and removing from the front of the queue
- **Duplicates**: Allows duplicate elements
- **Usage**: Suitable for scenarios like task scheduling, breadth-first search in graphs, and buffering data streams

Example:
```java
import java.util.LinkedList;
import java.util.Queue;

public class QueueExample {
    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.add("apple");
        queue.add("banana");
        queue.add("cherry");
        System.out.println(queue.peek()); // Output: apple
        System.out.println(queue.poll()); // Output: apple
        System.out.println(queue.poll()); // Output: banana
        System.out.println(queue.isEmpty()); // Output: false
    }
}
```

### PriorityQueue
- **Type**: Queue
- **Implementation**: Binary heap
- **Order**: Elements are ordered according to their natural ordering or by a comparator provided at queue construction time
- **Access**: Elements are accessed based on priority, not insertion order
- **Duplicates**: Allows duplicate elements
- **Usage**: Suitable for scenarios where elements need to be processed based on priority, such as task scheduling

Example:
```java
import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(5);
        pq.add(1);
        pq.add(3);
        System.out.println(pq.poll()); // Output: 1 (smallest element)
        System.out.println(pq.poll()); // Output: 3
        System.out.println(pq.poll()); // Output: 5
    }
}
```

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
