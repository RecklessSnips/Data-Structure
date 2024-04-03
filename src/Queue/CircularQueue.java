package Queue;

public class CircularQueue {

    /*
    当我们原本的queue拥有固定的大小，当enqueue满，且dequeue到了最后，
    我们浪费了其他所有的空间，无法继续添加新的数据。这时候我们需要用circular queue
    来进行永恒的添加。

    那么如何确定一个circular queue是full的状态呢？

    首先，一定是根据增加rear的大小来添加数据，减少front的大小来删减数据，又因为是一个
    circular queue，所以必然rear有朝一日可能会跟front相撞，也就是说：即将添加的
    新数据，可能会跟front冲突：

    Queue:  1    2    3    4    5
          front                rear

    当我们想从此时的 5后面添加一个6，根据circular queue，需要添加到queue的开头，
    也就是top此时的位置，但是top此时是满的。那么我们如何判断这个条件？

    前面提到了，因为我们要加新的数据，那么rear的值一定要增加，而且是增加 1. 所以，我们要用
    rear + 1 来跟front比较，很明显一定会有index out of bound，那么，就证明我们需要做的的不是
    直接判断rear和front是否冲突，而是“下一个所被添加的元素的 INDEX 是否会跟当前front的位置冲突”
    那么就需要根据此时rear的位置 +1， 再根据queue的size来判断这个数据的index，我们就需要用到 %。

    所以，如果此时我们的rear在index是4的位置，rear + 1 = 5， 那么 5%size -> 5%5 = 0
    也就是说，下一个数据要在的位置是在queue的 0 的位置，刚好跟top冲突，所以此时证明queue是满的！

    Queue:  1    2    3    4    5
          rear                front

    如果此时rear在0，那么 (rear + 1) % 5 = 1， 也就是说下一个元素要被添加到 queue的 1 这个位置，
    显然有位置，所以此时queue不为空。

    Queue:  1    2    3    4    5
                          rear front

    此时 rear在 index 为 3 的位置，那么 (rear + 1) % 5 = 4，也就是说下一个元素要被添加到
     queue的 4 这个位置，但是被此时的front占用了，所以是满的。

    这样一来，就知道了如何用 (rear + 1) % Queue.size 来判断当前queue是否是满的，
    并且确定新的rear的位置！
     */

    private int max_Size;
    private int[] queue;
    private int front;
    private int rear;

    public CircularQueue(int size){
        max_Size = size;
        queue = new int[size];
        // head指针：front 直接指向head，初始为-1，但是当第一次加入之后，需要在enqueue里手动设置为 0，否则
        // 会有问题。之后的添加都由 dequeue 完成。
        front = -1;
        // tail指针：默认queue里无元素，而且rear指向的是queue的第一个元素
        // rear直接代表的是当前元素的index
        rear = -1;
    }

    public boolean isFull(){
        // 这里 front + 1 是因为最开始的时候front设为了 -1，所以以后每次想要访问front的真实值，
        // 需要将 front + 1
        return (rear + 1) % max_Size == front;
    }

    // 很重要，如果只有当front 和 rear 都为 -1 了，说明满了，这个由dequeue来设置
    public boolean isEmpty(){
        return front == -1 && rear == -1;
    }


//    Queue:  1    2    3    4    5
//    Index:  0    1    2    3    4
//                               rear
    public void enQueue(int value){
        if (isFull()){
            System.out.println("Queue is full!!!");
            return;
        }
        if (isEmpty()) {
            front = 0; // Initialize front to 0 when adding the first element
        }
        rear = (rear + 1) % max_Size;
        queue[rear] = value;
    }

//    Queue:  1    2    3    4    5
//    Index:  0    1    2    3    4
//           rear                top
    public int deQueue(){
        if (isEmpty()){
            throw new RuntimeException("Queue is Empty!!!");
        }
        // 保存当前 front 所指的数据
        int value = queue[front];
        // 更新 front 的位置
        if (front == rear){
            // 如果相同，那么说明满了，将front 和 rear全部重设，更加简便
            front = -1;
            rear = -1;
        }else {
            front = (front + 1) % max_Size;
        }
        return value;
    }

    public void show(){
        if (isEmpty()){
            System.out.println("Queue is empty!!!");
            return;
        }
        // 正常顺序, 因为每次如果清理完queue，front 和 rear 都会被 reset 成 -1
        // else 是为了判断 queue 里只有一个数据的情况
        if (front < rear){
            System.out.print("[ ");
            for (int i = front; i <= rear ; i++) {
                System.out.print(queue[i] + ", ");
            }
            System.out.print("]\n");
        }else {
            System.out.print("[ ");
            System.out.print(queue[rear] +  " ");
            System.out.print("]\n");
        }
    }

    public static void main(String[] args) {
        CircularQueue queue = new CircularQueue(5);
//        Queue: 1 2 3 4 5
//        Index: 0 1 2 3 4
        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.enQueue(6);
        queue.enQueue(6);
        queue.show();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.deQueue();
        queue.show();
        queue.enQueue(6);
        queue.enQueue(3);
        queue.enQueue(2);
        queue.show();
        queue.deQueue();
        queue.deQueue();
        queue.show();
    }

}
