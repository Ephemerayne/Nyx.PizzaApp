package space.lala.nyxpizzaapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPizzasApi {

    @GET("nyx-pizza-app/pizzas.json")
    Call<List<Pizza>> getPizzas();
}
