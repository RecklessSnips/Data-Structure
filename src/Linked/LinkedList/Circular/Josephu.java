package Linked.LinkedList.Circular;

import Linked.LinkedList.Circular.HeroList;
import Linked.LinkedList.Circular.Hero;

import java.util.Iterator;
import java.util.LinkedList;

public class Josephu {
    /*
    解决约瑟夫环问题：
    https://www.youtube.com/watch?v=oalevb7BV_c&list=PLmOn9nNkQxJFvyhDYx0ya4F75uTtUHA_f&index=29

    根据用户输入的出征间隔间距，输出英雄的出征顺序
     */
    private HeroList heroList;

    public Josephu(HeroList heroList){
        this.heroList = heroList;
    }

    /*
    输入一个出征的间隔数，英雄会从第一个开始循环，每隔 i 个就依次出征，最后输出顺序
     */
    public String getAdventure(int i){
        StringBuilder builder = new StringBuilder();
        // 这里的head是我们要删除的，不再是head.next了 (因为本来head是空的node，遍历 i 次刚好就是目标node)
        Hero head = heroList.getHead().getNext();
        // 当还剩下一个node的时候停止，直接拿出即可
        while (heroList.getSize() > 1){
            /*
             因为是从本node开始算 i 次，并且从第一个node，而不是head开始，所以 - 1就能刚好
             落到想要的node上
             */
            for (int j = 0; j < i-1; j++) {
                head = head.getNext();
            }
            /*
             因为我们的特殊Circular HeroList的 28 行 set到是 head，所以需要通过 getNext()
             取到真实的值
             */
            if (head == heroList.getHead()){
                head = heroList.getHead().getNext();
            }
            // 加入答案
            builder.append(head.getName()).append(" ");
            // 因为我们要删除这个 node，但是需要从这个node的下一个node开始下一次计数，要保存下一个的值
            Hero next = head.getNext();
            /*
            同理，如果拿到的是head，那么用 getNext 取值
             */
            if (next == heroList.getHead()){
                next = heroList.getHead().getNext();
            }
            // 删除这个node
            heroList.delete(head.getId());
//            System.out.println("list: ");
//            heroList.show();
            // 最后更新一下下次的起点，因为我们是从circular里轮着出征，直到所有的英雄都出征，list归为0
            head = next;
        }
        builder.append(heroList.getHead().getNext().getName());
        return builder.toString();
    }

    public static void main(String[] args) {
        HeroList heroList = new HeroList();
        heroList.add(new Hero(4, "天闲星", "入云龙", "公孙胜"));
        heroList.add(new Hero(1, "天魁星", "及时雨", "宋江"));
        heroList.add(new Hero(3, "天机星", "智多星", "吴用"));
        heroList.add(new Hero(14, "天伤星", "行者", "武松"));
        heroList.add(new Hero(9, "天英星", "小李广", "花荣"));
        heroList.add(new Hero(2, "天罡星", "玉麒麟", "卢俊义"));
        System.out.println("出征顺序:");
        Josephu circle = new Josephu(heroList);
        System.out.println(circle.getAdventure(2));
    }

}
