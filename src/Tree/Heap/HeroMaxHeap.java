package Tree.Heap;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class HeroMaxHeap {

    private Hero[] heap;

    private int length;

    // 为了支撑单独 delete 方法，而特意加的变量，没别的用处
    private int sortedLength;

    public HeroMaxHeap(){

    }

    public HeroMaxHeap(Hero[] heroes){
        heap = heroes;
        length = heap.length;
        sortedLength = length;
    }

    public Hero getRoot(){
        return heap[0];
    }

    public int getLength(){
        return length;
    }

    /*
    在 PriorityQueue中, 手动将arraylist里转化后，添加到heap里，为了执行heapify
     */
    public void setArray(Hero[] heroes){
        heap = new Hero[heroes.length];
        System.arraycopy(heroes, 0, heap, 0, heroes.length);
        length = heap.length;
        sortedLength = length;
    }

    /*
    用 bottom up construction （heapify）直接将一个现有的数组转换成一个 heap，比一个个添加更高效： O(n) < O(nlogn)
     */
    public void heapify(){
        /*
        从第一个non-leaf node ( floor(n/2) - 1) 开始检查，
        当前sub tree（也就三个node：parent，left，right）是否构成一个Max Heap
        并且以parent交换之后的sub tree开始，重复这个步骤，直到形成一个max heap.
         */
        // 拿到第一个最大的 non-leaf node 的 index
        int nonLeafIndex = (int) Math.floor(length / 2) - 1;
        // 开始不断检查，因为heap是一个 complete binary tree，所以找到这个index后，一直递减1，直到检查完root就行
        while (nonLeafIndex >= 0){
            downHeap(heap[nonLeafIndex], nonLeafIndex, length);
            nonLeafIndex--;
        }
    }

    // 实现
    public void heapSort(){
        /*
         从 array heap的倒数第1个位置开始给sort好的部分留出位置，
         当array不为sort的大小小鱼0时，停止sort（也就是pdf里红线前半段）
         */
        for (int i = length - 1; i > 0; i--) {
            Hero deleted = heap[0];
            heap[0] = heap[--length];
            heap[length] = deleted;
            downHeap(heap[0], 0, i);
        }
    }

    // 单独将root删除，并且每次删除后维护这个max heap
    public Hero delete(){
        /*
        实质上是将root和最后一个leaf的位置交换，然后在后面的操作中不考虑这个被移到最后面的root了，它会被移到
        heap的最尾部，因为这是In place；当最小的node被删除，整个array就是一个sort好的，这就是heap sort
         */
        // 第一步：将root和heap尾部的node互换位置
        Hero deleted = heap[0];
        heap[0] = heap[--sortedLength];
        heap[sortedLength] = deleted;
        /*
         第二步，对新的root进行down Heap
         一定要注意这里，因为在每一次删除的过程中，被交换位置的，新的root会逐层down heap
         而有可能会跟刚刚跟它交换完位置的原来的root，再次交换，导致错误。所以我们每一次循环的时候
         都要提前把原本array heap的大小手动-1，为的是流出那部分，已经sort好的部分，不去比较！
         这样，我们的删除才算正确了！
         */
        downHeap(heap[0], 0, sortedLength - 1);
        return deleted;
    }

    /**
     *  TODO: 这个的方法的逻辑可以被优化，但是目前来说可以解决问题!
     *  检查：先让 left：2 * x + 1, right: 2 * x + 2 比较，找到最大的，然后跟当前parent比，如果parent大，那么是
     *  max heap，如果不是，交换位置。
     *  这个过程叫 downHeap
     * @param parent 第一个 non-leaf node
     * @param parentIndex 第一个 non-leaf node 的 index，需要这个来确定左右child的index
     * @param heapSize 当前array heap的大小，这一步只是为了在进行 In place 的 heap sort的时候
     *                 保持array的后半部分处于sort的位置，不让刚刚交换位置的root跟它进行比较
     *                 更多请看解释看 58 行
     */
    private void downHeap(Hero parent, int parentIndex, int heapSize){
        // 因为heap里存的是英雄，所以比较的时候拿Hero 的 reference来比较
        Hero left = null;
        Hero right = null;
        int leftIndex = -1;
        int rightIndex = -1;
        // 如果该leaf存在，那么让 left reference 它
        if (2 * parentIndex + 1 < heapSize){
            leftIndex = 2 * parentIndex + 1;
            left = heap[leftIndex];
            if (2 * parentIndex + 2 < heapSize){
                rightIndex = 2 * parentIndex + 2;
                right = heap[rightIndex];
            }
        }

//        System.out.println("Left: " + left);
//        System.out.println("LeftIndex: " + leftIndex);
//        System.out.println("Right: " + right);
//        System.out.println("RightIndex: " + rightIndex);
        /*
        考虑两种情况，如果只有左边，和两边都有。（因为heap是complete binary tree，要把node靠左放）
         */
        int max = parentIndex;
        if (left != null ){
            if (right != null){
                /*
                 两边都要比较, 比较出大的index，作为一会替换的地方，跟 parent比较，
                 如果parent小，说明不是 max heap
                 */
//                int max;
                if (left.getId() < right.getId()){
                    max = rightIndex;
                }else{
                    max = leftIndex;
                }
                // Max 作为所要替换的node的实际index，需要拿这个node的id跟parent比
//                System.out.println("Max index: " + max);
                if (parent.getId() < heap[max].getId()){
//                    System.out.println("ParentIndex: " + parentIndex);
                    Hero tmp = heap[parentIndex];
                    heap[parentIndex] = heap[max];
                    heap[max] = tmp;
                }
            }else{
                // 只和左边比较, 如果parent小，说明不是max heap
                if (parent.getId() < left.getId()){
                    max = leftIndex;
                    // 开始交换, 注意是在数组里交换，要用index来交换
                    Hero tmp = heap[parentIndex];
                    heap[parentIndex] = heap[max];
                    // 此时的heap[max] 是被换下来的，需要对它继续down heap
                    heap[max] = tmp;
                }
            }
        }
        // 说明当前的parent就是最大值，不需要进行下一次 downHeap
        if (max != parentIndex){
            downHeap(heap[max], max, heapSize);
        }
//        else{
//            System.out.println("已经是 Max heap，不需要 Down heap");
//        }
    }

    public void show(){
        for (Hero hero: heap){
            System.out.println(hero);
        }
    }

    // 打出未被sort的部分，主要给priority用
    public void show(int range){
        for (int i = 0; i < range; i++){
            System.out.println(heap[i]);
        }
    }

    public static void main(String[] args) {
        Hero[] heroes = new Hero[]{
                new Hero(15, "天勇星", "大刀", "关胜"),
                new Hero(4, "天闲星", "入云龙", "公孙胜"),
                new Hero(20, "天速星", "神行太保", "戴宗"),
                new Hero(1, "天魁星", "及时雨", "宋江"),
                new Hero(17, "天暗星", "青面兽", "杨志"),
                new Hero(10, "天贵星", "小旋风", "柴进"),
                new Hero(30, "天损星", "浪里白条", "张顺"),
                new Hero(9, "天英星", "小李广", "花荣"),
                new Hero(25, "天退星", "挿翅虎", "雷横"),
                new Hero(19, "天空星", "急先锋", "索超"),
                new Hero(23, "天微星", "九纹龙", "史进"),
        };
        HeroMaxHeap maxHeap = new HeroMaxHeap(heroes);
        long start = System.currentTimeMillis();
        maxHeap.heapify();
        for(Hero hero: heroes){
            System.out.println(hero);
        }
        long end = System.currentTimeMillis();
        // 最坏情况是 O(n) 内将数组转换成 Max Heap
        long time = end-start;
        System.out.println("用时 " + time + " 毫秒");
        maxHeap.delete();
        maxHeap.delete();
        for(Hero hero: heroes){
            System.out.println(hero);
        }

//        Hero[] heroes = new Hero[]{
//                new Hero(1, "天魁星", "及时雨", "宋江"),
//                new Hero(2, "天罡星", "玉麒麟", "卢俊义"),
//                new Hero(3, "天机星", "智多星", "吴用"),
//                new Hero(4, "天闲星", "入云龙", "公孙胜"),
//                new Hero(5, "天勇星", "大刀", "关胜"),
//                new Hero(6, "天雄星", "豹子头", "林冲"),
//                new Hero(7, "天猛星", "霹雳火", "秦明"),
//                new Hero(8, "天威星", "双鞭", "呼延灼"),
//                new Hero(9, "天英星", "小李广", "花荣"),
//                new Hero(10, "天贵星", "小旋风", "柴进"),
//                new Hero(11, "天富星", "扑天雕", "李应"),
//                new Hero(12, "天满星", "美髯公", "朱仝"),
//                new Hero(13, "天孤星", "花和尚", "鲁智深"),
//                new Hero(14, "天伤星", "行者", "武松"),
//                new Hero(15, "天勇星", "大刀", "关胜"),
//                new Hero(16, "天捷星", "没羽箭", "张清"),
//                new Hero(17, "天暗星", "青面兽", "杨志"),
//                new Hero(18, "天祐星", "金枪手", "徐宁"),
//                new Hero(19, "天空星", "急先锋", "索超"),
//                new Hero(20, "天速星", "神行太保", "戴宗"),
//                new Hero(21, "天异星", "赤髪鬼", "刘唐"),
//                new Hero(22, "天杀星", "黑旋风", "李逵"),
//                new Hero(23, "天微星", "九纹龙", "史进"),
//                new Hero(24, "天究星", "没遮拦", "穆弘"),
//                new Hero(25, "天退星", "挿翅虎", "雷横"),
//                new Hero(26, "天寿星", "混江龙", "李俊"),
//                new Hero(27, "天剑星", "立地太岁", "阮小二"),
//                new Hero(28, "天平星", "船火儿", "张横"),
//                new Hero(29, "天罪星", "短命二郎", "阮小五"),
//                new Hero(30, "天损星", "浪里白条", "张顺"),
//                new Hero(31, "天败星", "活阎罗", "阮小七"),
//        };
//        HeroMaxHeap maxHeap = new HeroMaxHeap(heroes);
//        long start = System.currentTimeMillis();
//        maxHeap.heapify();
//        for(Hero hero: heroes){
//            System.out.println(hero);
//        }
//        long end = System.currentTimeMillis();
//        // 最坏情况是 O(n) 内将数组转换成 Max Heap
//        long time = end-start;
//        System.out.println("用时 " + time + " 毫秒");
//
//        long start1 = System.currentTimeMillis();
//        maxHeap.heapSort();
//        long end1 = System.currentTimeMillis();
//        long time1 = end1-start1;
//        for(Hero hero: heroes){
//            System.out.println(hero);
//        }
//        System.out.println("Heap sort用时 " + time1 + " 毫秒");
    }
    /*
        1,  天魁星   及时雨   宋江
        2	天罡星	玉麒麟	卢俊义
        3	天机星	智多星   吴用
        4	天闲星	入云龙   公孙胜
        5	天勇星	大刀	    关胜
        6	天雄星	豹子头   林冲
        7	天猛星	霹雳火	秦明
        8	天威星	双鞭	    呼延灼
        9	天英星	小李广   花荣
        10	天贵星	小旋风	柴进
        11	天富星	扑天雕	李应
        12	天满星	美髯公	朱仝
        13	天孤星	花和尚	鲁智深
        14	天伤星	行者   武松
        15	天立星	双枪将	董平
        16	天捷星	没羽箭	张清
        17	天暗星	青面兽	杨志
        18	天祐星	金枪手	徐宁
        19	天空星	急先锋	索超
        20	天速星	神行太保	戴宗
        21	天异星	赤髪鬼	刘唐
        22	天杀星	黑旋风   李逵
        23	天微星	九纹龙	史进
        24	天究星	没遮拦	穆弘
        25	天退星	挿翅虎	雷横
        26	天寿星	混江龙	李俊
        27	天剑星	立地太岁	阮小二
        28	天平星	船火儿	张横
        29	天罪星	短命二郎	阮小五
        30	天损星	浪里白条	张顺
        31	天败星	活阎罗	阮小七
        32	天牢星	病关索	杨雄
        33	天慧星	拼命三郎	石秀
        34	天暴星	两头蛇	解珍
        35	天哭星	双尾蝎	解宝
        36	天巧星	燕小乙	燕青
     */
}
