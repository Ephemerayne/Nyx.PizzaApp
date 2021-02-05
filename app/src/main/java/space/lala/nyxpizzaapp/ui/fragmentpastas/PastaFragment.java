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

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.ProductsAdapter;
import space.lala.nyxpizzaapp.ui.activitydetailproducts.PastaDetailActivity;

public class PastaFragment extends Fragment {

    ProductsAdapter adapter;
    private PastaFragmentViewModel pastaFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pasta, container, false);
        RecyclerView pastaRecycler = view.findViewById(R.id.pasta_recycler);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
      pastaRecycler.setLayoutManager(layoutManager);
      adapter = new ProductsAdapter();

      pastaRecycler.setAdapter(adapter);
      pastaFragmentViewModel = ViewModelProviders.of(this).get(PastaFragmentViewModel.class);
      pastaFragmentViewModel.getPastas(Product.Type.Pasta)
              .observe(this, pastas -> adapter.setProducts(pastas));

        adapter.setListener(new ProductsAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PastaDetailActivity.class);
                intent.putExtra(PastaDetailActivity.EXTRA_PASTA_ID, position);
                getActivity().startActivity(intent);
            }
        });
        return pastaRecycler;
    }
}
