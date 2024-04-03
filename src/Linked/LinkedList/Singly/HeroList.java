package Linked.LinkedList.Singly;

import java.util.Random;

public class HeroList {
    /*
     head不存放数据，它的next指向表里的第一个数据，需要通过head来拿到链表的所有数据
     后面引出的tmp就是head，他们的next指向的才是真实的数据
     */
    private Hero head;

    public HeroList(){
        this.head = new Hero(0, "", "", "");
    }

    public Hero getHead(){
        return head;
    }

    public void setHead(Hero hero){
        this.head = hero;
    }

    /*
    加到链表的最后，需要一个辅助node来拿到head，然后开始找到最后一个node
     */
    public void addHero(Hero hero){
        Hero tmp = getHead();
        while (tmp.next != null){
            /*
            要按照hero的id大小按照顺序插入
            找到第一个next的位置，比所加入的node大的，加入到tmp和tmp.next的中间
             */
            if (tmp.next.getId() > hero.getId()){
                hero.next = tmp.next;
                tmp.next = hero;
                // 加入完毕后立马退出
                return;
            }
            else if (tmp.next.getId() == hero.getId()){
                System.out.println("英雄已被添加!");
                return;
            }
            tmp = tmp.next;
        }
        // 说明当前Hero ID 是最大的，应该加到末尾
        tmp.next = hero;
    }

    // 根据id俩修改某个node的信息
    public void amend(Hero hero){
        Hero tmp = getHead();
        while (tmp.next != null){
            if (tmp.next.getId() == hero.getId()){
                tmp.next.setConstellation(hero.getConstellation());
                tmp.next.setName(hero.getName());
                tmp.next.setNickname(hero.getNickname());
                return;
            }
            tmp = tmp.next;
        }
        System.out.println("未找到该英雄");
    }

    // 根据id删除某个英雄
    public Hero delete(int id){
        Hero tmp = getHead();
        Hero deleted = null;
        if (tmp.next == null){
            System.out.println("无英雄!");
            return null;
        }
        while (tmp.next != null){
            if (tmp.next.getId() == id){
                deleted = tmp.next;
                tmp.next = deleted.next;
                break;
            }
            tmp = tmp.next;
        }
        return deleted;
    }

    /*
    传入一个链表的头节点，得到这个链表的大小
     */
    public int getSize(){
        int counter = 0;
        Hero tmp = getHead();
        while (tmp.next != null){
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
        Hero tmp = getHead();
        while (tmp.next != null){
            // 从第一个node开始
            tmp = tmp.next;
            System.out.println(tmp);
        }
    }

    public static void main(String[] args) {
        HeroList heroList = new HeroList();
        heroList.show();
        System.out.printf("英雄个数: %d\n", heroList.getSize());
        heroList.addHero(new Hero(4, "天闲星", "入云龙", "公孙胜"));
        heroList.addHero(new Hero(1, "天魁星", "及时雨", "宋江"));
        heroList.addHero(new Hero(3, "天机星", "智多星", "吴用"));
        heroList.addHero(new Hero(14, "天伤星", "行者", "武松"));
        heroList.addHero(new Hero(9, "天英星", "小李广", "花荣"));
        heroList.addHero(new Hero(2, "天罡星", "玉麒麟", "卢俊义"));
        heroList.show();
        System.out.printf("英雄个数: %d\n", heroList.getSize());
        System.out.println("After amend");
        heroList.amend(new Hero(1, "ss", "???", "..."));
        heroList.show();
        heroList.delete(3);
        heroList.delete(14);
        heroList.delete(9);
        System.out.println("After delete");
        heroList.show();
        System.out.printf("英雄个数: %d\n", heroList.getSize());

        // 执行以下性能测试需要将上面main的部分注释掉，还要把addHero里判断ID相同的部分去掉
//        Random random = new Random();
//        long startTime = System.currentTimeMillis();
//        for (int i = 0; i < 59999; i++) {
//            int id = random.nextInt(59999) + 1;
//            heroList.addHero(new Hero(id, "天罡星", "玉麒麟", "卢俊义"));
//        }
//        long endTime = System.currentTimeMillis();
//        // 计算并打印执行时间
//        long duration = endTime - startTime;
//        System.out.println("执行时间：" + duration + " 毫秒");
//        System.out.println("执行时间：" + duration / 1000 + " 秒");
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
