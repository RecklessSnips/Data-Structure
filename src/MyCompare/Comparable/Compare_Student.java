package MyCompare.Comparable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Compare_Student{

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Alice", 101));
        students.add(new Student("Bob", 102));
        students.add(new Student("Charlie", 100));

        Collections.sort(students);

        for (Student student : students) {
            System.out.println(student.name + " - " + student.id);
        }
    }

}
