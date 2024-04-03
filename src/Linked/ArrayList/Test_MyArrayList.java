package Linked.ArrayList;

import java.util.Iterator;

public class Test_MyArrayList {
    public static void main(String[] args) {
        MyArrayList<Integer> l = new MyArrayList<>();
        l.add(1);   // add 1st
        l.add(2);   // add 2nd
        l.add(3);   // add 3rd
        l.add(4);   // add 4th
        l.add(5);   // add 5th
        l.add(6);   // add 6th
        /*
        this is the second expand
         */
        l.add(7);   // add 6th
        l.display();
        System.out.println(l.size());
        System.out.println();
        Iterator<Integer> iterator1 = l.iterator();
        // the try-finally block similar to the defer in golang
        try {
            while (iterator1.hasNext()) {
                int next = iterator1.next();
                System.out.printf("Element is : %d\n", next);
            }
        }finally {
            System.out.println();
            System.out.println("The length is: " + l.size());
        }
        l.remove(1);
        l.display();
        System.out.println(l.size());
        System.out.println(l.getCapacity());
        Iterator<Integer> iterator = l.iterator();

        System.out.println();
        // the try-finally block similar to the defer in golang
        try {
            while (iterator.hasNext()) {
                int next = iterator.next();
                System.out.printf("Element is : %d\n", next);
            }
        }finally {
            System.out.println();
            System.out.println("The length is: " + l.size());
        }
    }
}
