package Stack;

import java.util.Stack;

public class PostfixEvaluation {

    private Stack<Integer> numberStack;

    public PostfixEvaluation(){
        numberStack = new Stack<>();
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

    public int evaluate(String express){
        String expression = express.replaceAll(" ", "");
        /*  这一次不需要operationStack，而是numberStack
            2 5 3 6 + * * 5 / 2 -
            2 * (5 * ( 3 + 6 ) ) / 5 - 2
        */

        for (int i = 0; i < expression.length(); i++) {
            if (Character.isDigit(expression.charAt(i))){
                numberStack.push(Character.getNumericValue(expression.charAt(i)));
            }else{
                int a = numberStack.pop(); // 先
                int b = numberStack.pop(); // 后
                char c = expression.charAt(i);
                int value = perform(a, b, c);
                numberStack.push(value);
            }
        }
        return numberStack.pop();
    }

    public static void main(String[] args) {
//        String postfix = "2 5 3 6 + * * 5 / 2 -";
//        String postfix = "23+";
//        String postfix = "234*+";
//        String postfix = "34*25*+";
        String postfix = "342+*5*";
        PostfixEvaluation postfixEvaluation =
                new PostfixEvaluation();
        int value = postfixEvaluation.evaluate(postfix);
        System.out.println(value);
    }
}
