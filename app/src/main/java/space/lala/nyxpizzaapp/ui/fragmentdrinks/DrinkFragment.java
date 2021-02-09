package space.lala.nyxpizzaapp.ui.fragmentdrinks;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import space.lala.nyxpizzaapp.ProductCheckBoxListener;
import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.ProductsAdapter;
import space.lala.nyxpizzaapp.ui.activitydetailproducts.ProductDetailActivity;

public class DrinkFragment extends Fragment implements ProductCheckBoxListener {

    ProductsAdapter adapter;
    private DrinkFragmentViewModel drinkFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        RecyclerView drinkRecycler = view.findViewById(R.id.drink_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        drinkRecycler.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(this::selectProduct);

        drinkRecycler.setAdapter(adapter);
        drinkFragmentViewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel.class);
        drinkFragmentViewModel.getDrinks(Product.Type.Drinks)
                .observe(this, drinks -> adapter.setProducts(drinks));

        adapter.setListener(new ProductsAdapter.Listener() {
            public void onClick(int id) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, id);
                getActivity().startActivity(intent);
            }
        });
        return drinkRecycler;
    }

    @Override
    public void selectProduct(int id, boolean isChecked) {

    }
}