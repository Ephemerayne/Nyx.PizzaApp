package space.lala.nyxpizzaapp;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface JsonProductsApi {

    @GET("nyx-pizza-app/products.json")
    Observable<List<Product>> getProducts();
}
