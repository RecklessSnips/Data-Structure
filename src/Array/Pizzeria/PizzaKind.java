package Array.Pizzeria;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;

public class PizzaKind {
    private final int kinds = 6;
    private String kind;
    private Queue<Pizza> pizzas;

    public PizzaKind(String k){
        kind = k;
        pizzas = new LinkedList<>() {
        };
    }

    public String getPizzaKind(){
        return this.kind;
    }

    public void addPizza(Pizza pizzaName){
        /*
        To reduce the time of checking, here I don't check
        Instead, I check in the pizzeria class!

        if (getPizzaKind().equals(pizzaName.getKind())){

        }
        */
        pizzas.add(pizzaName);
    }

    public Pizza seeOldestPizza(){
        if (pizzas == null){
            throw new NullPointerException("This kind is empty!");
        }
        return pizzas.peek();
    }

    public void retrievePizza(){
        if (pizzas == null){
            return;
        }
        pizzas.remove();
    }

    public int getKindLength(){
        return pizzas.size();
    }
    public void showAllPizzas(){
        for (Pizza p: pizzas) {
            System.out.printf("Cook: %s; Pizza: %s; Birth time: %d\n", p.getCook(), p.getKind(), p.getBirthtime());
        }
    }
}


