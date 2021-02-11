package space.lala.nyxpizzaapp;

import space.lala.nyxpizzaapp.model.Product;

public interface CartProductListener {

    void onAddRemoveButtonsClick(Product product);

    void onCartProductClick(Product product);
}
