package space.lala.nyxpizzaapp;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();

    private Listener listener;

    interface Listener {
        void onClick(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView v) {
            super(v);
            cardView = v;
        }
    }

    public void setProducts(List<Product> products) {
        this.products.clear();
        this.products.addAll(products.stream()
                .sorted(Comparator.comparing(Product::getTitle))
                .collect(Collectors.toList())
        );
        notifyDataSetChanged();
    }

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public CaptionedImagesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.card_captioned_image, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        setViewsForProducts(cardView, position);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClick(position);
                }
            }
        });
    }

    private void setViewsForProducts(CardView cardView, int position) {
        final Resources resources = cardView.getResources();
        ImageView imageView = cardView.findViewById(R.id.image);

        if (products.get(position).getImageURL() != null && !products.get(position).getImageURL().isEmpty()) {
            Picasso.with(cardView.getContext())
                    .load(products.get(position).getImageURL())
                    .fit().centerCrop()
                    .into(imageView, new Callback.EmptyCallback() {
                        @Override
                        public void onSuccess() {
                            super.onSuccess();
                            cardView.findViewById(R.id.shimmer).setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            super.onError();
                            imageView.setImageDrawable(cardView.getContext().getDrawable(R.drawable.pizza_error_loading));
                        }
                    });
        }

        imageView.setContentDescription(products.get(position).getTitle());
        TextView title = cardView.findViewById(R.id.title);
        TextView price = cardView.findViewById(R.id.price);
        title.setText(products.get(position).getTitle());
        price.setText(resources.getString(R.string.prices, products.get(position).getPrice()));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
