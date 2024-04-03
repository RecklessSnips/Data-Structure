package Tree.Heap;

import java.util.ArrayList;
import java.util.Arrays;

public class PriorityQueue {

    private HeroMaxHeap heap;

    private int length;

    private ArrayList<Hero> arrayList;

    public PriorityQueue(){
        heap = new HeroMaxHeap();
        length = 0;
        arrayList = new ArrayList<>();
    }

    public void enqueue(Hero hero){
        /*
         通过arraylist来添加，然后转换成Hero，复制到heap上，最后heapify一下
         这样的性能比直接heap.upHeap()要快，这样是 O(n)

         每次添加的时候 heapify，其实跟每次 upHeap 差不多，但是快
         */
        arrayList.add(hero);
        Hero[] heroes = arrayList.toArray(new Hero[0]);
        heap.setArray(heroes);
        length++;
        heap.heapify();
    }

    public Hero dequeue(){
        length--;
        return heap.delete();
    }

    public int getLength(){
        return length;
    }

    public void show(){
        // 打出未被sort的部分，也就是真正意义上的 queue 的部分，说明要在length以内
        heap.show(length);
    }

    public static void main(String[] args) {
        PriorityQueue pq = new PriorityQueue();
        pq.enqueue(new Hero(15, "天勇星", "大刀", "关胜"));
        pq.enqueue(new Hero(4, "天闲星", "入云龙", "公孙胜"));
        pq.enqueue(new Hero(20, "天速星", "神行太保", "戴宗"));
        pq.enqueue(new Hero(1, "天魁星", "及时雨", "宋江"));
        pq.enqueue(new Hero(17, "天暗星", "青面兽", "杨志"));
        pq.enqueue(new Hero(10, "天贵星", "小旋风", "柴进"));
        pq.enqueue(new Hero(30, "天损星", "浪里白条", "张顺"));
        pq.enqueue(new Hero(9, "天英星", "小李广", "花荣"));
        pq.enqueue(new Hero(25, "天退星", "挿翅虎", "雷横"));
        pq.enqueue(new Hero(19, "天空星", "急先锋", "索超"));
        pq.enqueue(new Hero(23, "天微星", "九纹龙", "史进"));

        long start = System.currentTimeMillis();
        System.out.println("length: " + pq.getLength());
        System.out.println("Deeeeeeeee" + pq.dequeue());
        System.out.println("Deeeeeeeee" + pq.dequeue());
        System.out.println("Deeeeeeeee" + pq.dequeue());
        System.out.println("Deeeeeeeee" + pq.dequeue());
        System.out.println("Deeeeeeeee" + pq.dequeue());
        System.out.println("Deeeeeeeee" + pq.dequeue());
        System.out.println("Deeeeeeeee" + pq.dequeue());
        System.out.println("length: " + pq.getLength());
        long end = System.currentTimeMillis();
        long time = start - end;
        System.out.println("用时 " + time + " 毫秒");
        pq.show();
    }
}
