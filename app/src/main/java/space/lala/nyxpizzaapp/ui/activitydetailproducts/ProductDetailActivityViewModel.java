package space.lala.nyxpizzaapp.ui.activitydetailproducts;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import io.reactivex.rxjava3.core.Single;
import space.lala.nyxpizzaapp.datasource.repository.ProductsRepository;
import space.lala.nyxpizzaapp.model.Product;

public class ProductDetailActivityViewModel extends AndroidViewModel {
    private ProductsRepository repository;

    public ProductDetailActivityViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
    }

    public LiveData<Product> getProduct(int id) {
        return repository.getProduct(id);
    }

    public Single<Product> getProductSingle(int id) {
        return repository.getProductSingle(id);
    }

    public void update(Product product) {
        repository.update(product);
    }
}
