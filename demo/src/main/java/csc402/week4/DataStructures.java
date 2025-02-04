package csc402.week4;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class DataStructures {
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
        stack.peek(); // Output: cherry
        System.out.println(stack.peek()); // Output: cherry
        System.out.println(stack.pop()); // Output: cherry
        System.out.println(stack.pop()); // Output: banana
        System.out.println(stack.isEmpty()); // Output: false

        Queue<String> queue = new LinkedList<>();
        queue.add("apple");
        queue.add("banana");
        queue.add("cherry");
        queue.peek(); // Output: apple
        System.out.println(queue.peek()); // Output: apple
        System.out.println(queue.poll()); // Output: apple
        System.out.println(queue.poll()); // Output: banana
        System.out.println(queue.isEmpty()); // Output: false



    

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
