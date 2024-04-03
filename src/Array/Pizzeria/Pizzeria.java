package Array.Pizzeria;

import java.util.Arrays;
import java.util.Iterator;

public class Pizzeria {
    private final int kindSize = 6;
    private PizzaKind[] pizzeria;

    public Pizzeria(){
        pizzeria = new PizzaKind[kindSize];
        // initializing pizza kinds
        pizzeria[0] = new PizzaKind("Pepperoni");
        pizzeria[1] = new PizzaKind("Hawaiian");
        pizzeria[2] = new PizzaKind("Cheese");
        pizzeria[3] = new PizzaKind("Marinara");
        pizzeria[4] = new PizzaKind("Veggie");
        pizzeria[5] = new PizzaKind("BBQ");
    }
    public void addPizza(Pizza pizza){
        /*
        similar to the A2, here for increasing readability
        I use Pizza as the type
        */
        String kind = pizza.getKind();
        for (PizzaKind pizzaKind : pizzeria){
            // to save time, thus we check(will maybe not...)
            if (pizzaKind.getPizzaKind().equals(kind)){
                pizzaKind.addPizza(pizza);
            }
        }
    }

    // customer retrieve a pizza by kind
    public String getPizza(String customer, String kind){
        // here we can generate a order!
        Pizza order = new Pizza(customer, kind);
        for (PizzaKind pizzaKind : pizzeria){
            if (pizzaKind.getPizzaKind().equals(kind)){
                pizzaKind.retrievePizza();
            }
        }
        return order.getKind();
    }

    public Pizza getSurprisePizza(String customer){
        Pizza order = new Pizza(customer, null);
        /*
        using an iterator to iterate the array, each time
        the peek() will return the oldest pizza!
        */
        Iterator<PizzaKind> iterator = Arrays.stream(pizzeria).iterator();
        Iterator<PizzaKind> iterator1 = Arrays.stream(pizzeria).iterator();
        // by default the first pizza in the pizzeria is the oldest
        Pizza oldest = null;
        // make sure the first non-null value in the pizzeria
        while (iterator1.hasNext()){
            PizzaKind p = iterator1.next();
            if (p.getKindLength() != 0){
                oldest = p.seeOldestPizza();
            }
        }
        while (iterator.hasNext()){
            //     data Type:  PizzaKind ->  Pizza
            PizzaKind kind = iterator.next();
            // if the oldest is null, then tmp is the oldest
            if (kind.getKindLength() != 0){
                Pizza tmp = kind.seeOldestPizza();
                if (oldest.getBirthtime() > tmp.getBirthtime()) {
                    oldest = tmp;
                }
            }
        }
        // after find the oldest pizza, we delete it from the pizzeria
        for (PizzaKind pizzaKind : pizzeria){
            if (pizzaKind.getPizzaKind().equals(oldest.getKind())){
                pizzaKind.retrievePizza();
                break;
            }
        }
        order.setKind(oldest.getKind());
        return order;
    }

    public void showPizzas(){
        int counter = 1;
        for (PizzaKind p: pizzeria) {
            System.out.printf("Pizza kind: %d \n", counter++);
            p.showAllPizzas();
        }
    }

    public static void main(String[] args) {
        Pizzeria pizzeria = new Pizzeria();

        Pizza pizza1 = new Pizza("Shuai", "Pepperoni");
        Pizza pizza2 = new Pizza("Jenny", "Hawaiian");
        Pizza pizza3 = new Pizza("Ahsoka", "BBQ");
        Pizza pizza4 = new Pizza("Anakin", "BBQ");
        Pizza pizza5 = new Pizza("Obiwan", "Cheese");
        Pizza pizza6 = new Pizza("Yoda", "Marinara");
        Pizza pizza7 = new Pizza("Doku", "Veggie");
        Pizza pizza8 = new Pizza("Vader", "Pepperoni");
        Pizza pizza9 = new Pizza("Palpatine", "Marinara");
        Pizza pizza10 = new Pizza("Maul", "Pepperoni");
        Pizza pizza11 = new Pizza("Savage", "Cheese");

        pizzeria.addPizza(pizza1);
        pizzeria.addPizza(pizza2);
        pizzeria.addPizza(pizza3);
        pizzeria.addPizza(pizza4);
        pizzeria.addPizza(pizza5);
        pizzeria.addPizza(pizza6);
        pizzeria.addPizza(pizza7);
        pizzeria.addPizza(pizza8);
        pizzeria.addPizza(pizza9);
        pizzeria.addPizza(pizza10);
        pizzeria.addPizza(pizza11);

        pizzeria.showPizzas();

        // getPizza()
        System.out.println("*************************");
        String order1 = pizzeria.getPizza("Chenxuan", "Pepperoni");
        System.out.println("Ordered: " + order1);
        pizzeria.showPizzas();

        System.out.println("*************************");
        String order2 = pizzeria.getPizza("Yuchen", "Veggie");
        System.out.println("Ordered: " + order2);
        pizzeria.showPizzas();

        // getSurprisePizza()
        for (int i = 0; i < 9; i++) {
            System.out.println("*************************");
            Pizza order = pizzeria.getSurprisePizza("ZhouXinTong");
            System.out.println("Ordered: " + order.getKind());
            pizzeria.showPizzas();
        }
    }
}
