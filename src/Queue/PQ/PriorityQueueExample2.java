package Queue.PQ;

import java.util.Comparator;
import java.util.PriorityQueue;

public class PriorityQueueExample2 {

    static class Student {
        String name;
        int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    static class PersonAgeComparator implements Comparator<Student> {
        @Override
        public int compare(Student person1, Student person2) {
            // Compare based on age
            return person1.name.compareTo(person2.name);
        }
    }

    public static void main(String[] args) {
        // Create a Priority Queue with a custom comparator
        PriorityQueue<Student> pq = new PriorityQueue<>(new PersonAgeComparator());

        // Adding students to the Priority Queue
        pq.add(new Student("Alice", 22));
        pq.add(new Student("Charlie", 25));
        pq.add(new Student("Bob", 18));

        // Display the students in the Priority Queue
        while (!pq.isEmpty()) {
            Student student = pq.poll();
            System.out.println("Name: " + student.name + ", Age: " + student.age);
        }
    }
}
