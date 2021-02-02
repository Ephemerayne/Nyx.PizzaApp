package space.lala.nyxpizzaapp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pasta {

    private String name;
    private int price;
    private String imageURL;

    public static final List<Pasta> pastas = new ArrayList<>(Arrays.asList(
            new Pasta("С сыром", 150, "R.drawable.cheese_pasta"),
            new Pasta("С капустой и овощами", 190, "R.drawable.cabbage_vegetables_pasta"),
            new Pasta("С цыпленком", 220, "R.drawable.chicken_pasta"),
            new Pasta("С кукурузой и салатом", 160, "R.drawable.corn_salad_pasta"),
            new Pasta("С лососем", 300, "R.drawable.fish_pasta"),
            new Pasta("С мясом и морковью", 260, "R.drawable.meat_carrot_pasta"),
            new Pasta("С грибами в сливочном соусе", 200, "R.drawable.mushrooms_pasta"),
            new Pasta("С креветками", 280, "R.drawable.shrimp_pasta"),
            new Pasta("C базиликом и помидорами", 180, "R.drawable.vegan_pasta")
            ));

    private Pasta(String name, int price, String imageURL) {
        this.name = name;
        this.price = price;
        this.imageURL = imageURL;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageURL() {
        return imageURL;
    }
}

