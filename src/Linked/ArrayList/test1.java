package Linked.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class test1<AnyType> implements Iterable<AnyType> {
    private static final int DEFAULT_CAPACITY = 5;
    private int size;
    private AnyType[] array;

    public test1(){
        array = (AnyType[]) new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    // 一切跟array list有关的考虑size
    // 需要扩大底层和交换element的需要考虑底层array
    public void clear(){
        this.size = 0;
    }

    public int size(){
        // 当前array list的长度
        return this.size;
    }

    public int getCapacity(){
        // 底层array的长度为capacity
        return array.length;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void expandCapacity(int newCapacity){
        if (newCapacity < this.size)
            return;
        // create a copy of the current array
        AnyType[] copy = array;
        array = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            array[i] = copy[i];
            // when copy's length is max, the rest of array
            // will set to be null
        }
    }

    public void trimToSize(){
        expandCapacity(size());
    }

    public AnyType get(int index){
        if (index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();
        return array[index];
    }

    // save the old value (replaced value) to the return clause
    public AnyType set(int index, AnyType value){
        if (index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = array[index];
        array[index] = value;
        return old;
    }

    public boolean add(AnyType value){
        add(size() - 1, value);
        return true;
    }

    public void add(int index, AnyType value){
        if (index < 0 || index > size())
            throw new ArrayIndexOutOfBoundsException();
        if (getCapacity() == index)
            expandCapacity(size() * 2 + 1);
        for (int i = index + 1; i < size(); i++) {
            array[i] = array[i - 1];
        }
        array[index] = value;
        this.size++;
    }

    /*
    Similar to the add
    那 些 位 于 指 定 位 置 上 或 指 定 位 置 后 的 元 素 向 低 位 移 动 一 个 位 置
     */
    public AnyType remove(int index){
        AnyType old = array[index];
        array[index] = null;
        for (int i = index; i < size() - 1; i++) {
            array[i] = array[i + 1];
        }
        array[size() - 1] = null;
        this.size--;
        return old;
    }

    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType>{
        private int current = 0;

        public boolean hasNext(){
            return current < size;
        }

        public AnyType next(){
            if (!hasNext())
                throw new NoSuchElementException();
            // ++ automatically plus 1 onto current
            return array[current++];
        }

        // just call the remove form the list
        public void remove(){
            test1.this.remove(current--);
        }
    }

    public void display(){
        for (AnyType v : array)
            System.out.println(v);
    }
}


