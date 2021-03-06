package space.lala.nyxpizzaapp.ui;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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

import space.lala.nyxpizzaapp.ProductCheckBoxListener;
import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.utils.PriceFormatter;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> products = new ArrayList<>();

    private Listener listener;
    private ProductCheckBoxListener checkBoxListener;

    public ProductsAdapter(ProductCheckBoxListener checkBoxListener) {
        this.checkBoxListener = checkBoxListener;
    }

    public interface Listener {
        void onClick(int id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;

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
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_adapter, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        setViewsForProducts(cardView, position);
        final Product product = products.get(position);
        cardView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onClick(product.getId());
            }
        });
        CheckBox checkBox = cardView.findViewById(R.id.checkbox_product);
        checkBox.setChecked(product.isSelected());
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkBoxListener.selectProduct(product.getId(), checkBox.isChecked());
            }
        });
    }

    private void setViewsForProducts(CardView cardView, int position) {
        final Resources resources = cardView.getResources();
        final Product product = products.get(position);
        setProductImage(cardView, position);

        TextView title = cardView.findViewById(R.id.title);
        TextView priceTextView = cardView.findViewById(R.id.price);
        title.setText(product.getTitle());
        final double price = product.getPrice();
        priceTextView.setText(resources.getString(R.string.prices, PriceFormatter.format(price)));
        View checkedView = cardView.findViewById(R.id.checked_view);
        if (product.isSelected()) {
            checkedView.setVisibility(View.VISIBLE);
        } else {
            checkedView.setVisibility(View.GONE);
        }
    }

    private void setProductImage(CardView cardView, int position) {
        ImageView imageView = cardView.findViewById(R.id.image);
        if (products.get(position).getImageURL() != null && !products.get(position).getImageURL().isEmpty()) {
            Picasso.with(cardView.getContext())
                    .load(products.get(position).getImageURL())
                    .fit()
                    .centerCrop()
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
    }

    @Override
    public int getItemCount() {
        return products.size();
    }
}
