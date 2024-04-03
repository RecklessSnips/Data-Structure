package MyCompare.Comparable;

public class Student implements Comparable<Student> {
    /*
    Comparable define a natural ordering for a class, specifies how
    instances of that class can be compared to each other.
    只有implement 这个comparable的类之间可以互相比较

     Only have 1 method:

     int compareTo(T o);

     The compareTo() method returns a negative integer, zero, or a positive integer
     based on whether the current object is less than, equal to, or greater than
     the specified object o.
     */
    String name;
    int id;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int compareTo(Student other) {
        /*
        x -> first, y -> second
        -1 -> x < y
        0  -> x = y
        1  -> x > y
         */
        return Integer.compare(this.id, other.id);
    }
}
