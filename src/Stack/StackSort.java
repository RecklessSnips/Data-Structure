package Stack;

import java.util.Stack;

public class StackSort {

    // 给一个stack，只允许用另一个stack和一个var将给定的stack排序好

    public static Stack<Integer> stackSort(Stack<Integer> stack){
        int tmp = 0;
        Stack<Integer> myStack = new Stack<>();
        // 1. 先从原先stack取出一个放到我们的stack里
        myStack.push(stack.pop());
        // 2. 然后从第二个开始跟我们的stack比较，如果top小于我们的stack，那么直接push
        while (!stack.isEmpty()){
            // 记录从myStack每次拿出了多少元素到stack里
            int counter = 0;
            if (stack.peek() < myStack.peek()){
                myStack.push(stack.pop());
            }
            /* 3. 如果大于，那么将值复制到tmp里，开始依次跟我们的stack开始比较，如果tmp > MyStack.peek() 那么就
            将我们的stack的顶部放到原先stack里，这样可以保证我们的stack里的东西一直都是sort好的  */
            tmp = stack.pop();
            // 注意先用isEmpty判断，而不是 tmp > myStack.peek()，否则报错
            while (!myStack.isEmpty() && tmp > myStack.peek()){
                stack.push(myStack.pop());
                counter++;
            }
            /* 4. 直到tmp < MyStack.peek()，将tmp放入进去，然后依次将原先的stack里头，我们刚刚push进去的所有的值全部放回到
            我们的stack当中，依次循环, 就可以了  */
            myStack.push(tmp);
            for (int i = 0; i < counter; i++) {
                myStack.push(stack.pop());
            }
            System.out.println(myStack);
        }
        return myStack;
    }

    // 第二种写法
//    public static void stackSort(Stack<Integer> stack) {
//        if (stack.isEmpty()) {
//            return;
//        }
//
//        Stack<Integer> myStack = new Stack<>();
//        while (!stack.isEmpty()) {
//            int tmp = stack.pop();
//            int counter = 0; // Reset counter for each iteration
//
//            // Move elements from myStack to stack if they are less than tmp
//            while (!myStack.isEmpty() && myStack.peek() > tmp) {
//                stack.push(myStack.pop());
//                counter++;
//            }
//
//            // Push the current element to its correct position in myStack
//            myStack.push(tmp);
//
//            // Move back the elements from stack to myStack
//            for (int i = 0; i < counter; i++) {
//                myStack.push(stack.pop());
//            }
//        }
//
//        // Transfer the sorted elements back to the original stack
//        while (!myStack.isEmpty()) {
//            stack.push(myStack.pop());
//        }
//    }

    public static void main(String[] args) {
        Stack<Integer> s = new Stack<>();
        s.push(12);
        s.push(6);
        s.push(7);
        s.push(2);
        s.push(3);
        s.push(5);
        System.out.println(s);
        Stack<Integer> stack = stackSort(s);
        System.out.println(stack);
    }
}
