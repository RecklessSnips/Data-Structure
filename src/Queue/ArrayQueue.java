package Queue;

import com.sun.source.tree.BreakTree;

import java.util.LinkedList;
import java.util.Queue;

public class ArrayQueue {

    private int max_size;
    private int front;    // head (include the 1st)
    private int rear;     // tail (current data's next 会空出一个position)
    private int[] queue;        // 底层array


    public ArrayQueue(int size){
        this.queue = new int[size];
        this.max_size = size;
        // -1代表着front指向queue的head的前一个地方，而不是head本身
        // 最后遍历queue的时候需要将front+1才能拿到head
        this.front = -1;
        // rear直接代表queue的尾部，所以可以直接拿到，-1代表没有任何数据
        this.rear = -1;
    }

    // first lest check if the queue is full?
    public boolean isFull(){
        return rear == max_size - 1;
    }

    // if is empty?
    public boolean isEmpty() {
        return front == rear;
    }

    // 拿到head
    public int getHead(){
        return queue[front+1];
    }
    // 拿到tail
    public int getTail(){
        return queue[rear];
    }
    // 插入
    public void enQueue(int num){
        if (isFull()){
            System.out.println("Queue is full!");
            return;
        }
        rear++;
        queue[rear] = num;
    }

    // 删除
    public int deQueue(){
        if (isEmpty()){
            System.out.println("Queue is empty!");
            throw new RuntimeException("队列为空");
        }
        return queue[++front];
    }

    // 显示
    public void show(){
        if (isEmpty()){
            System.out.println("Queue is empty, can't show");
            return;
        }
        System.out.print("[ ");
        // 最后遍历queue的时候需要将front+1才能拿到head，然后必须包含rear本身，才能拿到
        for (int i = front + 1; i <= rear; i++){
            System.out.print(queue[i] + ", ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(5);
        queue.enQueue(1);
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.enQueue(2);
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.enQueue(3);
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.enQueue(4);
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.enQueue(5);
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.deQueue();
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.deQueue();
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.deQueue();
        queue.deQueue();
        System.out.println("Head is: " + queue.getHead());
        System.out.println("Tail is: " + queue.getTail());
        queue.show();
    }
}
