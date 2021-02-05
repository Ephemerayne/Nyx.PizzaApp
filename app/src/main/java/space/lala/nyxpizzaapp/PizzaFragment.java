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

public class PizzaFragment extends Fragment {

    CaptionedImagesAdapter adapter;
    private PizzaViewModel pizzaViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza, container, false);
        RecyclerView pizzaRecycler = (RecyclerView) view.findViewById(R.id.pizza_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);
        adapter = new CaptionedImagesAdapter();

        pizzaRecycler.setAdapter(adapter);
        pizzaViewModel = ViewModelProviders.of(this).get(PizzaViewModel.class);
        pizzaViewModel.getPizzas(Product.Type.Pizza)
                .observe(this, pizzas -> adapter.setProducts(pizzas));

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