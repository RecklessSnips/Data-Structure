package Array.Pizzeria;

public class Pizza {
    private static int pizzaTimeCounter = 0;
    private long birthTime;

    private String cook;
    private String kind;

    public Pizza(String name, String kind){
        cook = name;
        this.kind = kind;
        this.birthTime = System.currentTimeMillis() + pizzaTimeCounter++;
    }

    public void setKind(String kind){
        this.kind = kind;
    }
    public String getCook(){
        return cook;
    }
    public String getKind(){
        return kind;
    }

    public long getBirthtime(){
        return this.birthTime;
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "birthTime=" + birthTime +
                ", cook='" + cook + '\'' +
                ", kind='" + kind + '\'' +
                '}';
    }
}
