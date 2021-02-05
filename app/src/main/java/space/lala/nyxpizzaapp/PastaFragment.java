package space.lala.nyxpizzaapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PastaFragment extends Fragment {

    List<String> pastaNames = new ArrayList<>();
    List<Integer> pastaPrices = new ArrayList<>();
    List<String> pastaImages = new ArrayList<>();
    CaptionedImagesAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView pastaRecycler = (RecyclerView) inflater.inflate(R.layout.fragment_pasta, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://yarobest.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

//        JsonPastasApi jsonPastasApi = retrofit.create(JsonPastasApi.class);
//        Call<List<Pasta>> call = jsonPastasApi.getPastas();

//        call.enqueue(new Callback<List<Pasta>>() {
//            @Override
//            public void onResponse(Call<List<Pasta>> call, Response<List<Pasta>> response) {
//                if (!response.isSuccessful()) {
//                    Toast.makeText(getContext(), "ERROR: " + response.code(), Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//                List<Pasta> pastas = response.body();
//
//                for (int i = 0; i < pastas.size(); i++) {
//                   pastaNames.add(pastas.get(i).getName());
//                }
//                for (int i = 0; i < pastas.size(); i++) {
//                    pastaPrices.add(pastas.get(i).getPrice());
//                }
//                for (int i = 0; i < pastas.size(); i++) {
//                    pastaImages.add(pastas.get(i).getImageURL());
//                }
//                pastaRecycler.setAdapter(adapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<Pasta>> call, Throwable t) {
//
//            }
//        });


//        adapter = new CaptionedImagesAdapter(pastaNames, pastaPrices, pastaImages);
//        pastaRecycler.setAdapter(adapter);
//        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
//        pastaRecycler.setLayoutManager(layoutManager);
//
//        adapter.setListener(new CaptionedImagesAdapter.Listener() {
//            public void onClick(int position) {
//                Intent intent = new Intent(getActivity(), PastaDetailActivity.class);
//                intent.putExtra(PastaDetailActivity.EXTRA_PASTA_ID, position);
//                getActivity().startActivity(intent);
//            }
//        });
        return pastaRecycler;
    }
}
