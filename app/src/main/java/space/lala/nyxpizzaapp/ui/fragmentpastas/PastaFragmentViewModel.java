package space.lala.nyxpizzaapp.ui.fragmentpastas;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import space.lala.nyxpizzaapp.datasource.repository.ProductsRepository;
import space.lala.nyxpizzaapp.model.Product;

public class PastaFragmentViewModel extends AndroidViewModel {
    private ProductsRepository repository;

    public PastaFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new ProductsRepository(application);
    }

    public LiveData<List<Product>> getPastas() {
        return repository.getAllProducts(Product.Type.Pasta);
    }

    public Single<Product> getProductSingle(int id) {
        return repository.getProductSingle(id);
    }

    public void update(Product product) {
        repository.update(product);
    }
}
