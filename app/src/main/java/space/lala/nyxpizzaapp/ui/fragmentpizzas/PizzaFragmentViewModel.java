package space.lala.nyxpizzaapp.ui.fragmentpizzas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import space.lala.nyxpizzaapp.datasource.repository.ProductsRepository;
import space.lala.nyxpizzaapp.model.Product;

public class PizzaFragmentViewModel extends AndroidViewModel {
    private ProductsRepository repository;

    public PizzaFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
    }

    public LiveData<List<Product>> getPizzas(Product.Type type) {
        return repository.getAllProducts(type);
    }
}
