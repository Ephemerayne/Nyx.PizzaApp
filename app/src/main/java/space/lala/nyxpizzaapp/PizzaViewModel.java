package space.lala.nyxpizzaapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import space.lala.nyxpizzaapp.repository.ProductsRepository;

public class PizzaViewModel extends AndroidViewModel {
    private ProductsRepository repository;

    public PizzaViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
    }

    public LiveData<List<Product>> getPizzas(Product.Type type) {
        return repository.getAllProducts(type);
    }
}
