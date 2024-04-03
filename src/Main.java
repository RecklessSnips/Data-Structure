import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(6);
        list.add(5);
        list.add(1);
        list.add(4);
        list.add(2);
        removeEvensVer2(list);

        // just to test if the array have its default values
        int[] s = new int[5];
        s[0] = 1;
        for (int i : s)
            System.out.println(i);
        System.out.println(s.length);
    }

    /*
    When you modify a collection while iterating over it,
    a ConcurrentModificationException can occur because the iterator does not expect
    the collection to change during iteration. In this case, the iterator used
    by the enhanced for-loop is not aware that elements are being removed from the list,
    and so it may become invalid, leading to the exception.
     */
    public static void removeEvensVer2 ( List<Integer> lst ){

        /*   This will cause a ConcurrentModificationException since
        我们不能期待增强的for 循环懂得只有 当一项不被删除时它才必须向前推进
        for (Integer i : lst)
            if (i % 2 == 0)
                lst.remove(i);
         */
    }
}