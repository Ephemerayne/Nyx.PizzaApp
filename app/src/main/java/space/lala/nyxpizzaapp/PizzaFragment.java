package space.lala.nyxpizzaapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import space.lala.nyxpizzaapp.repository.ProductsRepository;

public class PizzaFragment extends Fragment {

    List<String> pizzaNames = new ArrayList<>();
    List<Double> pizzaPrices = new ArrayList<>();
    List<String> pizzaImages = new ArrayList<>();
    CaptionedImagesAdapter adapter;
    ProductsRepository productsRepository;
    private PizzaViewModel pizzaViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);

        pizzaViewModel = ViewModelProviders.of(this).get(PizzaViewModel.class);
        pizzaViewModel.getPizzas(Product.Type.Pizza).observe(this, pizzas -> pizzaRecycler.setAdapter(adapter));

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