package space.lala.nyxpizzaapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

public class PizzaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pizzaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pizza, container, false);

        List <String> pizzaNames = new ArrayList(Pizza.pizzas.size());

        for (int i = 0; i < Pizza.pizzas.size(); i++) {
            pizzaNames.add(Pizza.pizzas.get(i).getName());
        }

        List <Integer> pizzaPrice = new ArrayList(Pizza.pizzas.size());

        for (int i = 0; i < Pizza.pizzas.size(); i++) {
            pizzaPrice.add(Pizza.pizzas.get(i).getPrice());
        }

        List <Integer> pizzaImages = new ArrayList(Pizza.pizzas.size());

        for (int i = 0; i < Pizza.pizzas.size(); i++) {
            pizzaImages.add(Pizza.pizzas.get(i).getImageResourceId());
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pizzaNames, pizzaPrice, pizzaImages);
        pizzaRecycler.setAdapter(adapter);
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