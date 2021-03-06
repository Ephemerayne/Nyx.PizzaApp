package space.lala.nyxpizzaapp.ui;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import space.lala.nyxpizzaapp.datasource.repository.ProductsRepository;
import space.lala.nyxpizzaapp.model.Product;

public class BaseActivityViewModel extends AndroidViewModel {

    ProductsRepository repository;

    public BaseActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
    }

    public LiveData<List<Product>> getSelectedProducts() {
        return repository.getSelectedProducts();
    }
}
