package space.lala.nyxpizzaapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPastasApi {

    @GET("nyx-pizza-app/pastas.json")
    Call<List<Pasta>> getPastas();
}
