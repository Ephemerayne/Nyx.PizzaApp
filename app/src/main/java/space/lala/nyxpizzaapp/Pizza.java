package space.lala.nyxpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pizza {

    private String name;
    private int price;
    private int imageResourceId;

public static final List <Pizza> pizzas = new ArrayList<>(Arrays.asList(
        new Pizza("Маргарита", 250, R.drawable.pizza_margarita),
        new Pizza("Гавайская", 320, R.drawable.pizza_gavai),
        new Pizza("Четыре сыра", 300, R.drawable.pizza_cheese),
        new Pizza("Мясная", 350, R.drawable.pizza_meat),
        new Pizza("Оливки и базилик", 290, R.drawable.pizza_olive_basilik),
        new Pizza("Пепперони", 340, R.drawable.pizza_pepperoni),
        new Pizza("Руккола", 350, R.drawable.pizza_rukkola),
        new Pizza("Сервелат", 400, R.drawable.pizza_servelat),
        new Pizza("Овощная", 380, R.drawable.pizza_vegetables)
));

  private Pizza(String name, int price, int imageResourceId) {
      this.name = name;
      this.price = price;
      this.imageResourceId = imageResourceId;
  }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
