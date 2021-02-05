package space.lala.nyxpizzaapp.ui.activitydetailproducts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import space.lala.nyxpizzaapp.datasource.repository.ProductsRepository;
import space.lala.nyxpizzaapp.model.Product;

public class ProductActivityViewModel extends AndroidViewModel {
    private ProductsRepository repository;

    public ProductActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
    }

    public LiveData<Product> getProduct(int id) {
        return repository.getProduct(id);
    }
}
