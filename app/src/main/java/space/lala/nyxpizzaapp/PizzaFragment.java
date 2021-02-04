package space.lala.nyxpizzaapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PizzaFragment extends Fragment {

    List<String> pizzaNames = new ArrayList<>();
    List<Integer> pizzaPrices = new ArrayList<>();
    List<String> pizzaImages = new ArrayList<>();
    CaptionedImagesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yarobest.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPizzasApi jsonPizzasApi = retrofit.create(JsonPizzasApi.class);
        Call<List<Pizza>> call = jsonPizzasApi.getPizzas();

        call.enqueue(new Callback<List<Pizza>>() {
            @Override
            public void onResponse(Call<List<Pizza>> call, Response<List<Pizza>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "ERROR: " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Pizza> pizzas = response.body();

                for (int i = 0; i < pizzas.size(); i++) {
                    pizzaNames.add(pizzas.get(i).getName());
                }
                for (int i = 0; i < pizzas.size(); i++) {
                    pizzaPrices.add(pizzas.get(i).getPrice());
                }
                for (int i = 0; i < pizzas.size(); i++) {
                    pizzaImages.add(pizzas.get(i).getImageURL());
                }
                pizzaRecycler.setAdapter(adapter);
            }

        productsRepository = new ProductsRepository(getActivity().getApplication());

        productsRepository.getAllProducts(Product.Type.Pizza).observe(this, pizzas -> {
            pizzaNames.clear();
            pizzaPrices.clear();
            pizzaImages.clear();

            for (Product pizza : pizzas) {
                pizzaNames.add(pizza.getTitle());
                pizzaPrices.add(pizza.getPrice());
                pizzaImages.add(pizza.getImageURL());
            }
        });

        adapter = new CaptionedImagesAdapter(pizzaNames, pizzaPrices, pizzaImages);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PizzaDetailActivity.class);
                intent.putExtra(PizzaDetailActivity.EXTRA_PIZZA_ID, position);
                getActivity().startActivity(intent);
            }
        });
        return pizzaRecycler;
    }
}