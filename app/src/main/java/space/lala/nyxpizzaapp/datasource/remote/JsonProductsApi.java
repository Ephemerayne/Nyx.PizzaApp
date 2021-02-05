package space.lala.nyxpizzaapp.datasource.remote;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import space.lala.nyxpizzaapp.model.Product;

public interface JsonProductsApi {

    @GET("nyx-pizza-app/products.json")
    Observable<List<Product>> getProducts();
}
