package space.lala.nyxpizzaapp;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CaptionedImagesAdapter extends RecyclerView.Adapter<CaptionedImagesAdapter.ViewHolder> {

    private List<String> names;
    private List<Integer> prices;
    private List<String> imagesURLs;

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

    public CaptionedImagesAdapter(
            List<String> names,
            List<Integer> prices,
            List<String> imagesURLs
    ) {
        this.names = names;
        this.prices = prices;
        this.imagesURLs = imagesURLs;
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

        if (imagesURLs.get(position) != null && !imagesURLs.get(position).isEmpty()) {
            Picasso.with(cardView.getContext())
                    .load(imagesURLs.get(position))
                    .into(imageView);
        }
//        Drawable drawable = ContextCompat.getDrawable(cardView.getContext(), (String) imagesURLs.get(position));
//        imageView.setImageDrawable(drawable);
        // TODO: set image with picasso
        imageView.setContentDescription((CharSequence) names.get(position));
        TextView title = cardView.findViewById(R.id.title);
        TextView price = cardView.findViewById(R.id.price);
        title.setText((String) names.get(position));
        price.setText(resources.getString(R.string.prices, prices.get(position)));
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
