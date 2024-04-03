package Hash;

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
            }else if (tmp.next.getId() == hero.getId()){
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
            System.out.print(tmp + ", ");
        }
    }
}
