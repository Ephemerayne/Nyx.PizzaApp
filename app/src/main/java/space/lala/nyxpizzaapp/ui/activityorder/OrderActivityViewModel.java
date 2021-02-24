package space.lala.nyxpizzaapp.ui.activityorder;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import space.lala.nyxpizzaapp.datasource.repository.ProductsRepository;
import space.lala.nyxpizzaapp.datasource.repository.UserOrdersRepository;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.model.UserOrder;

public class OrderActivityViewModel extends AndroidViewModel {

    ProductsRepository repository;
    UserOrdersRepository ordersRepository;

    public OrderActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
        ordersRepository = new UserOrdersRepository(application);
    }

    public LiveData<List<Product>> getSelectedProducts() {
        return repository.getSelectedProducts();
    }

    public void update(Product product) {
        repository.update(product);
    }

    public double sumSelectedProductsPrices(List<Product> cartProducts) {
        return cartProducts.stream()
                .mapToDouble(product -> product.getPrice() * product.getQuantityOfSelectedProduct())
                .sum();
    }

    public int quantityOfSelectedProducts(List<Product> cartProducts) {
        return cartProducts.stream()
                .mapToInt(Product::getQuantityOfSelectedProduct).sum();
    }

    public void insertOrder(UserOrder userOrder) {
        ordersRepository.insertUserOrders(userOrder);
    }
}
