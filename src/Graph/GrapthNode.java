package Graph;

public class GrapthNode {

    private String name;
    private int num;
    private boolean isDeleted;

    public GrapthNode(String name, int num){
        this.name = name;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public String toString() {
        return "GrapthNode{" +
                "name='" + name + '\'' +
                ", num=" + num +
                '}';
    }
}
