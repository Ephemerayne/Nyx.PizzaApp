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

public class PastaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pastaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pasta, container, false);

        List<String> pastaNames = new ArrayList<>(Pasta.pastas.size());

        for (int i = 0; i < Pasta.pastas.size(); i++) {
            pastaNames.add(Pasta.pastas.get(i).getName());
        }
        List<Integer> pastaPrice = new ArrayList<>(Pasta.pastas.size());

        for (int i = 0; i < Pasta.pastas.size(); i++) {
            pastaPrice.add(Pasta.pastas.get(i).getPrice());
        }

        List<Integer> pastaImages = new ArrayList<>(Pasta.pastas.size());

        for (int i = 0; i < Pasta.pastas.size(); i++) {
            pastaImages.add(Pasta.pastas.get(i).getImageResourceId());
        }

        CaptionedImagesAdapter adapter = new CaptionedImagesAdapter(pastaNames, pastaPrice, pastaImages);
        pastaRecycler.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        pastaRecycler.setLayoutManager(layoutManager);

        adapter.setListener(new CaptionedImagesAdapter.Listener() {
            public void onClick(int position) {
                Intent intent = new Intent(getActivity(), PastaDetailActivity.class);
                intent.putExtra(PastaDetailActivity.EXTRA_PASTA_ID, position);
                getActivity().startActivity(intent);
            }
        });
        return pastaRecycler;
    }
}
