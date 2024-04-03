package MyCompare.Comparator_Person;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyComparator {
    /*
    Comparator allows you to define custom comparison logic for objects
    以不同的方式来比较，或者将未实现comparable的class进行排序
     */

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice", 30));
        people.add(new Person("Bob", 25));
        people.add(new Person("Charlie", 35));

        // Create a custom comparator to sort by name
        Comparator<Person> nameComparator = new Comparator<Person>() {
            @Override
            public int compare(Person s1, Person s2) {
                return s1.getName().compareTo(s2.getName());
            }
        };

        // Sort the list using the custom comparator
        Collections.sort(people, nameComparator);

        // Display the sorted list
        for (Person person : people) {
            System.out.println(person);
        }
    }
}
