package Linked.LinkedList.InterviewSingly;

import Linked.LinkedList.Singly.Hero;
import Linked.LinkedList.Singly.HeroList;

import java.util.LinkedList;
import java.util.Stack;

public class Reverse {
    /*
    反转一个单链表
     */
    private HeroList heroList;

    public Reverse (HeroList heroList){
        this.heroList = heroList;
    }

    // 用stack
    public LinkedList<Hero> reverse(){
        Stack<Hero> stack = new Stack<>();
        Hero head = heroList.getHead();
        // 放入stack
        while (head.getNext() != null){
            stack.push(head.getNext());
            head = head.getNext();
        };
        // 从stack拿出并且逆序
        LinkedList<Hero> reversedList = new LinkedList<>();
        while (!stack.isEmpty()){
            reversedList.add(stack.pop());
        }
        return reversedList;
    }

    // In place
    public void reverseInPlace(){
        /*
        用一个prev来储存上一个node，用tmp来储存当前
        先将当前node储存到tmp里用来遍历下一个node
        然后用当前node的next指向prev，所以最开始的时候prev要为null为了让head的next变为null
        最后通过先前保存的tmp来遍历下一个node
         */
        Hero head = heroList.getHead();
        Hero tmp = null;
        Hero prev = null;
        while (head != null){
            tmp = head.getNext();
            head.setNext(prev);
            prev = head;
            head = tmp;
        }
        // 一定要重新设置head! 但因为heroList打印的时候是从head的next开始，我们要小修改一下
        heroList.setHead(prev);
    }

    public void show(){
        Hero tmp = heroList.getHead();
        while (tmp.getNext() != null){
            System.out.println(tmp);
            tmp = tmp.getNext();
        }
    }

    public static void main(String[] args) {
        HeroList heroList = new HeroList();
        heroList.addHero(new Hero(4, "天闲星", "入云龙", "公孙胜"));
        heroList.addHero(new Hero(1, "天魁星", "及时雨", "宋江"));
        heroList.addHero(new Hero(3, "天机星", "智多星", "吴用"));
        heroList.addHero(new Hero(14, "天伤星", "行者", "武松"));
        heroList.addHero(new Hero(9, "天英星", "小李广", "花荣"));
        heroList.addHero(new Hero(2, "天罡星", "玉麒麟", "卢俊义"));
        heroList.show();
        Reverse reverse = new Reverse(heroList);
        System.out.println("After reverse");
        LinkedList<Hero> list = reverse.reverse();
        for (Hero hero: list){
            System.out.println(hero);
        }
        System.out.println("Reverse in place");
        reverse.reverseInPlace();
        reverse.show();
    }
}
