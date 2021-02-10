package space.lala.nyxpizzaapp;

import space.lala.nyxpizzaapp.model.Product;

public interface CartProductListener {

    void addRemoveQuantityOfProduct(Product product);
}
