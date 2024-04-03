package Stack;

import java.util.Stack;

public class InfixToPostfix {

    /* Convert a infix expression into postfix
    2 * (5 * ( 3 + 6 ) ) / 5 - 2
    --->
    2 5 3 6 + * * 5 / 2 -
     */
    private Stack<Character> operatorStack;

    public InfixToPostfix(){
        operatorStack = new Stack<>();
    }

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

    public String convert(String infix){
        String expression = infix.replaceAll(" ", "");
    /*  重点是，要将priority最高的，放到最前面，一个接着一个的放符号
        这一次不需要number stack，而是 operationStack
        2 * (5 * ( 3 + 6 ) ) / 5 - 2
        --->
        2 5 3 6 + * * 5 / 2 -
     */
        StringBuilder postfix = new StringBuilder();
        for (int i = 0; i < expression.length(); i++) {
            char current = expression.charAt(i);
            if (Character.isDigit(current)){
                postfix.append(current);
            }else{
                if (current == '('){
                    // 无脑加，等着 )
                    operatorStack.push(current);
                }else if (current == ')'){
                    while (operatorStack.peek() != '('){
                        // 将 左括号前面的expression全部加到postfix里
                        postfix.append(operatorStack.pop());
                    }
                    // 舍弃左括号
                    operatorStack.pop();
                }else{
                    // 判断该符号的priority是否高于当前顶部, 把高的，放到postfix里
                    // 不用考虑括号，这个只是在将片段放到postfix里，自然而然也就是priority最高的
                    if (!operatorStack.isEmpty() && operatorStack.peek() != '(' &&
                            operatorStack.peek() != ')' &&
//                            当顶部比下一个符号优先级高，就加到string后面
                            getPriority(operatorStack.peek()) >= getPriority(current)){
                        postfix.append(operatorStack.pop());
                    }
                    // 将新的符号放入stack
                    operatorStack.push(current);
                }
            }
        }
        while (!operatorStack.isEmpty()){
            postfix.append(operatorStack.pop());
        }

        return postfix.toString();
    }

    public static void main(String[] args) {
        InfixToPostfix infix = new InfixToPostfix();
        String postfix = infix.convert("2 * (5 * ( 3 + 6 ) ) / 5 - 2");

        // 从Postfix eval来帮助分析这个expression
        PostfixEvaluation postfixEvaluation = new PostfixEvaluation();
        int value = postfixEvaluation.evaluate(postfix);
        System.out.println(value);
    }
}
