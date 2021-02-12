package space.lala.nyxpizzaapp.ui.fragmentproducts;

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

public class ProductFragment extends Fragment implements ProductCheckBoxListener {

    private ProductsAdapter adapter;
    private ProductFragmentViewModel viewModel;
    private static final String TYPE = "type";
    private Product.Type type;

    public static ProductFragment newInstance(int ordinal) {
        ProductFragment productFragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putInt(TYPE, ordinal);
        productFragment.setArguments(args);
        return productFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product, container, false);
        RecyclerView productRecycler = view.findViewById(R.id.product_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        productRecycler.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(this::selectProduct);
        productRecycler.setAdapter(adapter);

        if (getArguments() != null) {
            int ordinal = getArguments().getInt(TYPE, -1);
            type = Product.Type.values()[ordinal];
        }

        viewModel = ViewModelProviders.of(this).get(ProductFragmentViewModel.class);

//        if (type == null) type = Product.Type.Pizza;

        viewModel.getProducts(type)
                .observe(getViewLifecycleOwner(), products -> adapter.setProducts(products));

        adapter.setListener(new ProductsAdapter.Listener() {
            public void onClick(int id) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, id);
                getActivity().startActivity(intent);
            }
        });
        return productRecycler;
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

                        if (isChecked) {
                            product.setQuantityOfSelectedProduct(1);
                        } else {
                            product.setQuantityOfSelectedProduct(0);
                        }

                        viewModel.update(product);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }
}
