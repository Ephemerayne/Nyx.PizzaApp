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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import space.lala.nyxpizzaapp.ProductCheckBoxListener;
import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.ProductsAdapter;
import space.lala.nyxpizzaapp.ui.activitydetailproducts.ProductDetailActivity;

public class DrinkFragment extends Fragment implements ProductCheckBoxListener {

    ProductsAdapter adapter;
    private DrinkFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_drinks, container, false);
        RecyclerView drinkRecycler = view.findViewById(R.id.drink_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        drinkRecycler.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(this::selectProduct);

        drinkRecycler.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(DrinkFragmentViewModel.class);
        viewModel.getDrinks()
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
        viewModel.getProductSingle(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Product>() {
                    @Override
                    public void onSuccess(@NonNull Product product) {
                        product.setSelected(isChecked);
                        viewModel.update(product);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}