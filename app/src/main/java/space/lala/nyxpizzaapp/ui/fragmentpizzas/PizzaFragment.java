package space.lala.nyxpizzaapp.ui.fragmentpizzas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.ProductsAdapter;
import space.lala.nyxpizzaapp.ui.activitydetailproducts.ProductDetailActivity;

public class PizzaFragment extends Fragment {

    ProductsAdapter adapter;
    private PizzaFragmentViewModel pizzaFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pizza, container, false);
        RecyclerView pizzaRecycler = view.findViewById(R.id.pizza_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pizzaRecycler.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter();

        pizzaRecycler.setAdapter(adapter);
        pizzaFragmentViewModel = ViewModelProviders.of(this).get(PizzaFragmentViewModel.class);
        pizzaFragmentViewModel.getPizzas(Product.Type.Pizza)
                .observe(this, pizzas -> adapter.setProducts(pizzas));

        adapter.setListener(new ProductsAdapter.Listener() {
            public void onClick(int id) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, id);
                getActivity().startActivity(intent);
            }
        });
        return pizzaRecycler;
    }
}