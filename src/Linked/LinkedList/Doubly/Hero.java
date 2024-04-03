package Linked.LinkedList.Doubly;

public class Hero {

    int id;
    String constellation;
    String nickname;
    String name;

    Hero next;
    Hero prev;

    public Hero(int id, String constellation, String nickname, String name) {
        this.id = id;
        this.constellation = constellation;
        this.nickname = nickname;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConstellation() {
        return constellation;
    }

    public void setConstellation(String constellation) {
        this.constellation = constellation;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hero getNext() {
        return next;
    }

    public void setNext(Hero next) {
        this.next = next;
    }

    public Hero getPrev() {
        return prev;
    }

    public void setPrev(Hero prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "id=" + id + ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", prev='" + getPrev().getNickname() + getPrev().getName() + '\'' ;
    }
}
