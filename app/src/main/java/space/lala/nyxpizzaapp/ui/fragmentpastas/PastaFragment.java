package space.lala.nyxpizzaapp.ui.fragmentpastas;

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

public class PastaFragment extends Fragment implements ProductCheckBoxListener {

    ProductsAdapter adapter;
    private PastaFragmentViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasta, container, false);
        RecyclerView pastaRecycler = view.findViewById(R.id.pasta_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pastaRecycler.setLayoutManager(layoutManager);
        adapter = new ProductsAdapter(this::selectProduct);

        pastaRecycler.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(PastaFragmentViewModel.class);
        viewModel.getPastas()
                .observe(this, pastas -> adapter.setProducts(pastas));

        adapter.setListener(new ProductsAdapter.Listener() {
            public void onClick(int id) {
                Intent intent = new Intent(getActivity(), ProductDetailActivity.class);
                intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, id);
                getActivity().startActivity(intent);
            }
        });
        return pastaRecycler;
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
