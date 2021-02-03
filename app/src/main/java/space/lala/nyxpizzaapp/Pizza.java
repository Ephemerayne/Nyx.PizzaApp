package space.lala.nyxpizzaapp;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pizza {

    private String name;
    private int price;

    @SerializedName("image")
    private String imageURL;
    
  private Pizza(String name, int price, String imageURL) {
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
