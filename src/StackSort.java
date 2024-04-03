import java.util.Stack;

public class StackSort {

    public static void sort(Stack<Integer> stack){
        if (stack == null)
            return;
        // top -> bottom
        // 5 3 2 7 6 12
        // ->
        // 2 3 5 7 12
        Stack<Integer> tmpStack = new Stack<>();
        // let the tmp stack decreasing order from top to bottom:
        // Remember: the tmpStack should be sorted!!! (Already)
        while (!stack.isEmpty()){
            int tmp = stack.pop();
            while (!tmpStack.isEmpty() && tmp < tmpStack.peek()){
                stack.push(tmpStack.pop());
            }
            tmpStack.push(tmp);
            System.out.println(tmpStack);
        }
        // 放回到原来stack
        while (!tmpStack.isEmpty()) {
            stack.push(tmpStack.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(12);
        s.push(6);
        s.push(7);
        s.push(2);
        s.push(3);
        s.push(5);
        System.out.println(s);
        sort(s);
        System.out.println(s);
    }
}
