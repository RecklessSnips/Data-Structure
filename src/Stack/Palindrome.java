package Stack;

import java.util.Stack;

public class Palindrome {

    public static boolean checkPalindrome(String sentence){
        Stack<Character> stack = new Stack<>();
        // 求出中点，将中电之前并包括中点都push到stack里
        // 34543 -> stack: 34
        // 432234 -> stack: 2
        int mid = sentence.length() / 2 - 1;
        for (int i = 0; i <= mid; i++) {
            stack.push(sentence.charAt(i));
        }
        // 不管中点，因为中点一样，从中点后一个点开始匹配，看是否一样
        // 如果是奇数，从 中点 + 2 开始匹配(跨过中点)，偶数从 中点 + 1 开始
        int start = 0;
        if (sentence.length() % 2 != 0){
            start = mid + 2;
        }else{
            start = mid + 1;
        }
        // 开始匹配, 从start开始一直到结束
        for (int i = start; i < sentence.length(); i++) {
            // 如果匹配成功，开始匹配stack的下一个字符
            if (stack.peek() != sentence.charAt(i)){
                break;
            }
            stack.pop();
        }
        // 如果匹配完成那么stack应该为空，否则就是每匹配完，不是palindrome
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(checkPalindrome("ahaha"));
        System.out.println(checkPalindrome("civic"));
        System.out.println(checkPalindrome("madam"));
        System.out.println(checkPalindrome("deified"));
        System.out.println(checkPalindrome("123321"));
        System.out.println(checkPalindrome("你是亿万富翁翁富万亿是你"));
    }
}
