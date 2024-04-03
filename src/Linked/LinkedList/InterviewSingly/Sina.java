package Linked.LinkedList.InterviewSingly;

import Linked.LinkedList.Singly.Hero;
import Linked.LinkedList.Singly.HeroList;

public class Sina {
    /*
    查找单链表的倒数第k个节点

    先得到链表个数，然后遍历到 size-k 停止，返回该Node
     */
    private HeroList heroList;

    public Sina (HeroList heroList){
        this.heroList = heroList;
    }

    public Hero getTheK(int k){
        Hero tmp = heroList.getHead();
        int size = heroList.getSize();
        int kth = size - k;
        for (int i = 0; i < kth; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getNext();
    }

    public static void main(String[] args) {
        // 手动创建链表
        HeroList heroList = new HeroList();
        heroList.addHero(new Hero(4, "天闲星", "入云龙", "公孙胜"));
        heroList.addHero(new Hero(1, "天魁星", "及时雨", "宋江"));
        heroList.addHero(new Hero(3, "天机星", "智多星", "吴用"));
        heroList.addHero(new Hero(14, "天伤星", "行者", "武松"));
        heroList.addHero(new Hero(9, "天英星", "小李广", "花荣"));
        heroList.addHero(new Hero(2, "天罡星", "玉麒麟", "卢俊义"));
        heroList.show();
        Sina sina = new Sina(heroList);
        int i = 1;
        System.out.println("\n倒数第 " + i + " 个英雄: " + sina.getTheK(i));
    }
}
