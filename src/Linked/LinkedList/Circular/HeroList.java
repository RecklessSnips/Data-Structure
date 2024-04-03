package Linked.LinkedList.Circular;

public class HeroList {

    /*
    实现一个环形链表
     */
    Hero head;

    public HeroList(){
        head = new Hero(0, "", "", "");
    }

    public Hero getHead() {
        return head;
    }

    public void add(Hero hero){
        // 拿到head node，用来判断何时停止添加
        Hero head = getHead();
        // 额外一个tmp的head node用来遍历
        Hero tmp = getHead();
        while (tmp.next != null && tmp.next != head){
            tmp = tmp.next;
        }
        tmp.next = hero;
        // 变成环形链表
        hero.next = head;
    }

    public void amend(Hero hero){
        Hero head = getHead();
        Hero tmp = getHead();
        while (tmp.next != head){
            if (tmp.getId() == hero.getId()){
                tmp.setConstellation(hero.getConstellation());
                tmp.setNickname(hero.getNickname());
                tmp.setName(hero.getName());
                return;
            }
            tmp = tmp.next;
        }
        System.out.println("英雄未找到");
    }

    public Hero delete(int id){
        Hero head = getHead();
        Hero tmp = getHead();
        Hero deleted = null;
        while (tmp.next != head){
            if (tmp.next.getId() == id){
                deleted = tmp.next;
                tmp.next = deleted.next;
            }
            tmp = tmp.next;
        }
        return deleted;
    }

    public int getSize(){
        int counter = 0;
        Hero head = getHead();
        Hero tmp = getHead();
        while (tmp.next != head && tmp.next != null){
            counter++;
            tmp = tmp.next;
        }
        return counter;
    }

    public boolean isEmpty(){
        return head.next == null;
    }

    public void show(){
        if (head.next == null){
            System.out.println("表为空!!!");
            return;
        }
        Hero head = getHead();
        Hero first = getHead().getNext();
        Hero tmp = getHead();
        while (tmp.next != head){
            System.out.println(tmp.next);
            tmp = tmp.next;
        }
        System.out.println("Head is: " + getHead().getNext());
        System.out.println("Next is: " + first);
    }

    public static void main(String[] args) {
        HeroList heroList = new HeroList();
        heroList.show();
        System.out.printf("英雄个数: %d\n", heroList.getSize());
        heroList.add(new Hero(4, "天闲星", "入云龙", "公孙胜"));
        heroList.add(new Hero(1, "天魁星", "及时雨", "宋江"));
        heroList.add(new Hero(3, "天机星", "智多星", "吴用"));
        heroList.add(new Hero(14, "天伤星", "行者", "武松"));
        heroList.add(new Hero(9, "天英星", "小李广", "花荣"));
        heroList.add(new Hero(2, "天罡星", "玉麒麟", "卢俊义"));
        heroList.show();
        System.out.printf("英雄个数: %d\n", heroList.getSize());
        heroList.amend(new Hero(14, "天祐星", "金枪手", "徐宁"));
        System.out.println("After amend");
        heroList.show();
        System.out.println("After delete");
        heroList.delete(4);
        heroList.show();
        System.out.println("After delete");
        heroList.delete(1);
        heroList.show();
        System.out.println("After delete");
        heroList.delete(2);
        heroList.show();
        System.out.println("After delete");
        heroList.delete(14);
        heroList.show();
    }

    /*
        1,  天魁星   及时雨   宋江
        2	天罡星	玉麒麟	卢俊义
        3	天机星	智多星   吴用
        4	天闲星	入云龙   公孙胜
        5	天勇星	大刀	    关胜
        6	天雄星	豹子头   林冲
        7	天猛星	霹雳火	秦明
        8	天威星	双鞭	    呼延灼
        9	天英星	小李广   花荣
        10	天贵星	小旋风	柴进
        11	天富星	扑天雕	李应
        12	天满星	美髯公	朱仝
        13	天孤星	花和尚	鲁智深
        14	天伤星	行者     武松
        15	天立星	双枪将	董平
        16	天捷星	没羽箭	张清
        17	天暗星	青面兽	杨志
        18	天祐星	金枪手	徐宁
        19	天空星	急先锋	索超
        20	天速星	神行太保	戴宗
        21	天异星	赤髪鬼	刘唐
        22	天杀星	黑旋风   李逵
        23	天微星	九纹龙	史进
        24	天究星	没遮拦	穆弘
        25	天退星	挿翅虎	雷横
        26	天寿星	混江龙	李俊
        27	天剑星	立地太岁	阮小二
        28	天平星	船火儿	张横
        29	天罪星	短命二郎	阮小五
        30	天损星	浪里白条	张顺
        31	天败星	活阎罗	阮小七
        32	天牢星	病关索	杨雄
        33	天慧星	拼命三郎	石秀
        34	天暴星	两头蛇	解珍
        35	天哭星	双尾蝎	解宝
        36	天巧星	燕小乙	燕青
     */
}
