package Hash;

public class HeroHashTable {

    /*
    为了快速存储和查找数据，用一个由 HeroList 组成的 Array来实现，没有复杂的 Hash(), 直接用
    要插入的 数 % size 即可，这样，每一个array的元素都是一个链表，每个链表存储着相对应的英雄

    为了形象，这个Array代表的是整个梁山泊，每个index代表的是梁山泊的某个区域，比如 区域1, 2, 3, etc... 每个区域
    居住的英雄都被 HeroList 记录，这样，通过访问梁山泊的不同区域，就可以得到这个区域的所有英雄，并且还可以通过查找
    英雄的 id，定位到其所在的区域
     */

    // 代表梁山泊
    // 注意这个 herolist 里的方法是自动排序好，所以即进去的英雄id永远是排序好的
    private HeroList[] liang;

    // 总共有多少区域
    private int size;

    public HeroHashTable(int size){
        this.size = size;
        liang = new HeroList[size];
        for (int i = 0; i < liang.length; i++) {
            liang[i] = new HeroList();
        }
    }

    // 用 hash 来确认这个英雄该去哪个区域当首领
    public int hash(int id){
        return id % size;
    }

    // 这个英雄上了梁山
    public void add(Hero hero){
        // 确认英雄该去哪个区域（加到哪个链表之后）
        int sector = hash(hero.getId());
        liang[sector].addHero(hero);
    }

    // 英雄下山
    public Hero delete(int id){
        // 得到英雄在哪个区域
        int sector = hash(id);
        return liang[sector].delete(id);
    }

    // 英雄更换
    public void amend(Hero hero){
        // 得到英雄在哪个区域
        int sector = hash(hero.getId());
        liang[sector].amend(hero);
    }

    // 查看所有的区域的英雄
    public void show(){
        for (int i = 0; i < liang.length; i++) {
            System.out.printf("第%d区域 英雄有 -> ", i);
            liang[i].show();
            System.out.println();
        }
    }

    // 查看英雄在哪个区域，传进去英雄的 id
    public int findHeroSector(int id){
        // 通过 hash 得到英雄在哪个区域
        return hash(id);
    }

    public static void main(String[] args) {
        HeroHashTable hashTable = new HeroHashTable(7);
        long startTime = System.nanoTime();
        hashTable.add(new Hero(1, "天魁星", "及时雨", "宋江"));
        hashTable.add(new Hero(2, "天罡星", "玉麒麟", "卢俊义"));
        hashTable.add(new Hero(3, "天机星", "智多星", "吴用"));
        hashTable.add(new Hero(4, "天闲星", "入云龙", "公孙胜"));
        hashTable.add(new Hero(5, "天勇星", "大刀", "关胜"));
        hashTable.add(new Hero(6, "天雄星", "豹子头", "林冲"));
        hashTable.add(new Hero(7, "天猛星", "霹雳火", "秦明"));
        hashTable.add(new Hero(8, "天威星", "双鞭", "呼延灼"));
        hashTable.add(new Hero(9, "天英星", "小李广", "花荣"));
        hashTable.add(new Hero(10, "天贵星", "小旋风", "柴进"));
        hashTable.add(new Hero(11, "天富星", "扑天雕", "李应"));
        hashTable.show();
        long endTime = System.nanoTime();

        // 计算并打印运行时间
        System.out.println("Execution time in nanoseconds: " + (endTime - startTime));
        System.out.println("Execution time in milliseconds: " + ((endTime - startTime) / 1_000_000.0));
        System.out.println();
        int i = 9;
        System.out.printf("英雄%d在第%d区域", i, hashTable.findHeroSector(i));
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
