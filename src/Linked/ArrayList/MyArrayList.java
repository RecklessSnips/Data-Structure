package Linked.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<AnyType> implements Iterable<AnyType> {
    private static final int DEFAULT_CAPACITY = 5;
    private int size;
    private AnyType[] arrayList;

    public MyArrayList(){
        arrayList = (AnyType[]) new Object[DEFAULT_CAPACITY];
        /* this line is trivial!
        this.size = arrayList.length;
         */
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
        return arrayList.length;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void trimToSize(){
        expandCapacity(size());
    }

    public AnyType get(int index){
        if (index < 0 || index > this.size)
            throw new ArrayIndexOutOfBoundsException();
        return arrayList[index];
    }

    // save the old value (replaced value) to the return clause
    public AnyType set(int index, AnyType value){
        if (index < 0 || index  > this.size)
            throw new ArrayIndexOutOfBoundsException();
        AnyType old = arrayList[index];
        arrayList[index] = value;
        return old;
    }

    public void expandCapacity(int newCapacity){
        if (newCapacity < this.size)
            return;
        AnyType[] old = arrayList;
        /*
          因 为 泛 型 数 组 的 创 建 是 非 法 的 。
          我 们 的 做 法 是 创 建一个泛型类型限界的数组，然后使用 一个数组进行类型转换。
          这將产生 一个编译器警告，但在泛型集合的实现中这是不可避免的
         */
        arrayList = (AnyType[]) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            arrayList[i] = old[i];
            // when copy's length is max, the rest of array
            // will set to be null
        }
    }

    public boolean add(AnyType value){
        // add at the end
        add(this.size, value);
        return true;
    }

    public void add(int index, AnyType value){
        /*
          or can use arrayList.length
          since arrayList can't call getCapacity() function
         */
        if (this.getCapacity() == index)
            // double the size; +1 is for the empty list case
            expandCapacity(size() * 2 + 1);
        for (int i = index + 1; i < size(); i++) {
            arrayList[i] = arrayList[i - 1];
        }
        arrayList[index] = value;
        this.size++;
    }

    /*
    Similar to the add
    那 些 位 于 指 定 位 置 上 或 指 定 位 置 后 的 元 素 向 低 位 移 动 一 个 位 置
     */
    public AnyType remove(int index){
        AnyType old = arrayList[index];
        try{
            for (int i = index; i < size() - 1; i++) {
                arrayList[i] = arrayList[i + 1];
            }
        }finally {
            // 让底层的array最后一个也为null
            arrayList[size() - 1] = null;
        }
        this.size--;
        return old;
    }

    public Iterator<AnyType> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<AnyType>{
        private int current = 0;
        public boolean hasNext(){
            return current < size();
        }

        public AnyType next(){
            if (!hasNext())
                throw new NoSuchElementException();
            // ++ automatically plus 1 onto current
            return arrayList[current++];
        }

        // just call the remove form the list
        public void remove(){
            MyArrayList.this.remove(current--);
        }
    }

    public void display(){
        for (AnyType v : arrayList)
            System.out.println(v);
    }
}

