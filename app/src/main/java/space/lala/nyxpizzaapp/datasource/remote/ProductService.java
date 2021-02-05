package space.lala.nyxpizzaapp.datasource.remote;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import space.lala.nyxpizzaapp.model.Product;


public class ProductService {

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://yarobest.ru/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    JsonProductsApi jsonProductsApi = retrofit.create(JsonProductsApi.class);

    public Observable<List<Product>> getProductsFromServer() {
        return jsonProductsApi.getProducts();
    }
}
