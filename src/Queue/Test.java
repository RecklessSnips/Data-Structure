package Queue;

import java.util.LinkedList;
import java.util.Queue;

public class Test {

    // CSI 3105 A1

    public static boolean test(String[] subset, String[] sequence){
        Queue<String > queue = new LinkedList<>();
        for (String num : subset) {
            queue.add(num);
        }
        LinkedList<String> linkedList = new LinkedList<>();
        for (String num : sequence) {
            linkedList.add(num);
        }

        while (!linkedList.isEmpty()){
            String tmp = linkedList.remove();
            if (tmp.equals(queue.peek())){
                queue.remove();
            }
        }
        return queue.isEmpty();
    }

    public static void main(String[] args) {
//        System.out.println(test(new int[]{1, 2, 3}, new int[]{1, 2, 3, 4}));
        System.out.println(test(new String[]{"buy Yahoo", "buy eBay", "buy Yahoo", "buy Oracle"},
                new String[]{"buy Amazon", "buy Yahoo", "buy eBay", "buy Yahoo", "buy Oracle"}));
    }
}
