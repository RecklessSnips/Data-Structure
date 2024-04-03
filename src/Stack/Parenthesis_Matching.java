package Stack;

import java.util.Stack;

public class Parenthesis_Matching {

    /*
    依次将sentence里的符号push到stack里，当遇到左括号，直接push，遇到右括号，检查当前栈顶是否是其匹配的左括号
    否则报错，如果有未闭合的左或者右括号，依然报错
     */
    public static boolean checkBracket(String sentence){
        Stack<Character> operatorStack = new Stack<>();
        for (int i = 0; i < sentence.length(); i++) {
            char current = sentence.charAt(i);
            switch (current){
                case '(':
                case '[':
                case '{':
                    operatorStack.push(current);
                    break;
                case ')':
                    if (operatorStack.isEmpty() || !operatorStack.peek().equals('(')){
                        System.out.println("Error: " + current + " at " + i);
                        return false;
                    }
                    operatorStack.pop();
                    break;
                case ']':
                    if (operatorStack.isEmpty() || !operatorStack.peek().equals('[')){
                        System.out.println("Error: " + current + " at " + i);
                        return false;
                    }
                    operatorStack.pop();
                    break;
                case '}':
                    if (operatorStack.isEmpty() || !operatorStack.peek().equals('{')){
                        System.out.println("Error: " + current + " at " + i);
                        return false;
                    }
                    operatorStack.pop();
                    break;
            }
        }
        // 此时stack里应该被清空 (如果没有，说明左括号没有被闭合）
        if (operatorStack.isEmpty()){
            return true;
        }else{
            System.out.println("Error: Unmatched opening " + operatorStack.peek());
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(checkBracket("c[d]")); // correct
        System.out.println(checkBracket("a{b[c]d}e")); // correct
        System.out.println(checkBracket("a{b(c]d}e")); // Incorrect
        System.out.println(checkBracket("a[b{c)dle}")); // Incorrect
        System.out.println(checkBracket("a{b(c)")); // Incorrect
        System.out.println(checkBracket("a{b(c[d]e)f}")); // correct
    }
}
