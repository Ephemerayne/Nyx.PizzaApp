package space.lala.nyxpizzaapp.navdrawer.ui.orderhistory;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.UserOrder;
import space.lala.nyxpizzaapp.utils.DateAndTimeFormatter;

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> {
    private List<UserOrder> userOrders = new ArrayList<>();

    public void setUserOrders(List<UserOrder> userOrders) {
        this.userOrders.clear();
        this.userOrders.addAll(userOrders);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public OrdersRecyclerAdapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        return new ViewHolder((CardView) LayoutInflater.from(parent.getContext()).inflate(
                R.layout.user_order_item, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersRecyclerAdapter.ViewHolder holder, int position) {
        CardView cardView = holder.cardView;
        final UserOrder userOrder = userOrders.get(position);
        final ProductAdapter productAdapter = new ProductAdapter(cardView.getContext());
        productAdapter.setProducts(userOrder.getProducts());
        TextView orderDateTime = cardView.findViewById(R.id.date_time_order);
        orderDateTime.setText(cardView.getResources().getString(
                R.string.order_datetime,
                DateAndTimeFormatter.formatDate(userOrder.getDateTimeOrder().toLocalDate()),
                DateAndTimeFormatter.formatTime(userOrder.getDateTimeOrder().toLocalTime())
        ));
        ListView listProducts = cardView.findViewById(R.id.list_products);
        listProducts.setAdapter(productAdapter);
    }

    @Override
    public int getItemCount() {
        return userOrders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cardView;

        public ViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }
    }
}
