package Stack;

import java.util.Scanner;

public class ArrayStack {

    // max size of the array
    private int max_size;

    // Top of the stack, no top initially
    private int top = -1;

    // the array stack
    private int[] stack;
    public ArrayStack(int size){
        this.stack = new int[size];
        this.max_size = size;
    }

    // Push
    public void push(int value){
        // if the list is full
        if (isFull()){
            System.out.println("Stack is full!!!");
            return;
        }
        // 因为top开始为-1
        top++;
        this.stack[top] = value;
    }

    // Pop the top value
    public int pop(){
        // if is empty
        if (isEmpty()){
            System.out.println("Stack is empty!!!");
            return 0;
        }
        int value = this.stack[top];
        top--;
        return value;
    }

    // determine if the stack is fulfilled
    public boolean isFull(){
        return top == max_size - 1;
    }
    // isEmpty
    public boolean isEmpty(){
        return top == -1;
    }

    // Peek
    public int peek(){
        return stack[top];
    }

    // print the stack from the top (of course from the top)
    public void asString(){
        if (isEmpty()){
            System.out.println("Stack is empty!!!");
        }
        // print from the top, and pop each value
        // 用top来循环，否则会有错误
        for (int i = top; i >= 0; i--) {
            System.out.printf("Stack value: %d\n", stack[i]);
        }
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(5);
        stack.push(5);
        stack.push(4);
        stack.push(3);
        stack.push(2);
        stack.push(1);
//        stack.push(1);
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.pop();
        stack.push(5);
        stack.push(4);
        stack.asString();
    }
}
