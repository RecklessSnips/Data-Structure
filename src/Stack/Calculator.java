package Stack;

import java.util.Arrays;
import java.util.Stack;

public class Calculator {

    /*
    计算器接受一个string表达式，通过 method calculate 来计算其结果，简便计算器
    不考虑个位数以上的数字，为了练习stack而不是别的！
    用stack实现：一个numberStack来接受数字，operatorStack来接受所有的符号，包括括号
    思路：当碰到一个符号优先级比此时operatorStack的top “小于等于” 的时候，将此时top之前，和numberStack一起运算了
    ，因为说明此时这个符号前面的所有符号都是同级的，可以直接从左到右，可以解决负数问题
    如果是带有括号，则将左括号push到operatorStack中，直到碰到右括号，然后开始计算当前所存储的numStack
    并且计算完成后将运算符去掉，直到遇到左括号停止，这样可以解决括号嵌套
     */
    private Stack<Integer> numberStack;
    private Stack<Character> operatorStack;

    public Calculator(){
        numberStack = new Stack<>();
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

    // calculation between 2 numbers
    private static int perform(Stack<Integer> s1, Stack<Character> s2){
        int a = s1.pop();
        int b = s1.pop();
        char c = s2.pop();
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

    public int calculate(String express){
        String expression = express.replaceAll(" ", "");
        for (int i = 0; i < expression.length(); i++) {
            // 如果是数字
            if (Character.isDigit(expression.charAt(i))){
                // 不考虑十位数以上的数字
                numberStack.push(Character.getNumericValue(expression.charAt(i)));
            }else{
                // 如果是符号
                if (expression.charAt(i) == '('){
                    operatorStack.push('(');
                } else if (expression.charAt(i) == ')') {
                    while (operatorStack.peek() != '('){
                        int value = perform(numberStack, operatorStack);
                        numberStack.push(value);
                    }
                    operatorStack.pop();
                }else{
                    // 普通符号
                    // 判断符号是否是负号而不是减法
                    if (expression.charAt(i) == '-' && ( i == 0 || expression.charAt(i - 1) == '(')){
                        // 处理 unary 的minus，往numberStack上push一个0，往后，0-3就可以代表-3
                        numberStack.push(0);
                    }
                    // 判断是否要进行优先级的计算
                    while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && operatorStack.peek() != ')'
                            && (getPriority(expression.charAt(i)) <= getPriority(operatorStack.peek()))){
                        int value = perform(numberStack, operatorStack);
                        numberStack.push(value);
                    }
                    operatorStack.push(expression.charAt(i));
                }
            }
//            System.out.println(numberStack);
//            System.out.println(operatorStack);
        }
        while (!operatorStack.isEmpty()){
            int value = perform(numberStack, operatorStack);
            numberStack.push(value);
        }
        return numberStack.pop();
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        // 完成！
        System.out.println(calculator.calculate("3 - 2 * 6 - 2"));
        System.out.println(calculator.calculate("2 * (5 *(3 - 2 * 6 - 2))/5 - 2"));
        System.out.println(calculator.calculate("2 * (5 *(3+6))/5-2"));
        System.out.println(calculator.calculate("2 * (5 *(-3-6))/5-2"));
        System.out.println(calculator.calculate("-3-6"));
    }
}
