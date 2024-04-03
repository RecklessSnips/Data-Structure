package Queue.PQ;

import java.util.PriorityQueue;

public class PriorityQueueExample1 {


    public static void main(String[] args) {
        // Create a Priority Queue
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Adding elements to the Priority Queue
        pq.add(5);
        pq.add(2);
        pq.add(8);
        pq.add(1);
        pq.add(9);

        // Display the elements in the Priority Queue
        System.out.println("Priority Queue: " + pq);

        // Removing elements from the Priority Queue
        // Removes and returns the element with the highest priority
        int removedElement = pq.poll();
        System.out.println("Removed Element: " + removedElement);

        // Display the elements after removal
        System.out.println("Priority Queue after removal: " + pq);

    }
}
