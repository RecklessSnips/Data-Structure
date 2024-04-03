package Stack;

import java.util.Stack;

public class Prayground {

    /*
    用来练习，中缀转后缀，计算后缀表达式
     */
    private static int getPriority(char c){
        if (c == '+' || c == '-'){
            return 0;
        }
        else if (c == '*' || c == '/'){
            return 1;
        }
        else{
            return -1;
        }
    }

    // calculation between 2 numbers
    private static int perform(int a, int b, char c){
        // 因为是从stack里pop出来的，用之前的减去（除以）后面的才是我们想要的结果
        switch (c){
            case '+':
                return a + b;
            case '-':
                return b - a;
            case '*':
                return a * b;
            case '/':
                return b / a;
        }
        return 0;
    }

    public static String infixToPostfix(String infix){
        /*
        2 * (5 * ( 3 + 6 ) ) / 5 - 2
        --->
        2 5 3 6 + * * 5 / 2 -
         */
        Stack<Character> operatorStack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        for (char c: infix.replaceAll(" ", "").toCharArray()){
            // 如果是数字直接加
            if (Character.isDigit(c)){
                builder.append(c);
            }else{
                // 如果是括号要加进去
                if (c == '('){
                    operatorStack.push(c);
                } else if (c == ')'){
                // 遇到括号要先算
                    while (operatorStack.peek() != '('){
                        builder.append(operatorStack.pop());
                    }
                    operatorStack.pop();
                } else if (!operatorStack.isEmpty() &&
                        operatorStack.peek() != '(' &&
                        operatorStack.peek() != ')' &&
                        getPriority(operatorStack.peek()) >= getPriority(c)) {
                // 要考虑优先级
                    builder.append(operatorStack.pop());
                    operatorStack.push(c);
                }else{
                  operatorStack.push(c);
                }
            }
        }
        while (!operatorStack.isEmpty()){
            builder.append(operatorStack.pop());
        }
        return builder.toString();
    }

    public static int calculate(String postfix){
        Stack<Integer> numberStack = new Stack<>();
//        Stack<Character> operatorStack = new Stack<>();   TODO: remove
        int result = 0;
        String post = postfix.replaceAll(" ", "");
        // 2 5 3 6 + * * 5 / 2 -
        for(char c: post.toCharArray()){
            if (Character.isDigit(c)) {
                numberStack.push(Character.getNumericValue(c));
            }else{
                int a = numberStack.pop();
                int b = numberStack.pop();
                int answer  = perform(a, b, c);
                numberStack.push(answer);
            }
        }
        return numberStack.pop();
    }

    public static void main(String[] args) {
        String postfix = infixToPostfix("2 * (5 * ( 3 + 6 ) ) / 5 - 2 + 6");
        System.out.println(postfix);
        System.out.println(calculate(postfix));
    }
}
