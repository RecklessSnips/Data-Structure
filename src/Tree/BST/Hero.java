package Tree.BST;

public class Hero {

    private int id;
    private String constellation;
    private String nickname;
    private String name;

    private Hero left;
    private Hero right;

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

    public Hero getLeft() {
        return left;
    }

    public void setLeft(Hero left) {
        this.left = left;
    }

    public Hero getRight() {
        return right;
    }

    public void setRight(Hero right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", constellation='" + constellation + '\'' +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'';
    }
}
