package space.lala.nyxpizzaapp;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.utils.PriceFormatter;

public class CartProductsAdapter extends RecyclerView.Adapter<CartProductsAdapter.ViewHolder> {
    private List<Product> cartProducts = new ArrayList<>();

    public CartProductsAdapter() {
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.cartProducts.clear();
        this.cartProducts.addAll(selectedProducts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView cv = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_selected_product_adapter, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        setViewsForSelectedProducts(cardView, position);
    }

    @Override
    public int getItemCount() {
        return cartProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

    }

    private void setViewsForSelectedProducts(CardView cardView, int position) {
        final Resources resources = cardView.getResources();

        setSelectedProductImage(cardView, position);

        TextView title = cardView.findViewById(R.id.cart_product_name);
        TextView priceTextView = cardView.findViewById(R.id.cart_product_price);
        title.setText(cartProducts.get(position).getTitle());
        final double price = cartProducts.get(position).getPrice();
        priceTextView.setText(resources.getString(R.string.prices, PriceFormatter.format(price)));
    }

    private void setSelectedProductImage(CardView cardView, int position) {
        ImageView imageView = cardView.findViewById(R.id.cart_product_image);
        if (cartProducts.get(position).getImageURL() != null && !cartProducts.get(position).getImageURL().isEmpty()) {
            Picasso.with(cardView.getContext())
                    .load(cartProducts.get(position).getImageURL())
                    .fit()
                    .centerCrop()
                    .into(imageView, new Callback.EmptyCallback() {
                        @Override
                        public void onSuccess() {
                            super.onSuccess();
//                            cardView.findViewById(R.id.shimmer).setVisibility(View.GONE);
                        }

                        @Override
                        public void onError() {
                            super.onError();
                            imageView.setImageDrawable(cardView.getContext().getDrawable(R.drawable.pizza_error_loading));
                        }
                    });
        }

        imageView.setContentDescription(cartProducts.get(position).getTitle());
    }

}
