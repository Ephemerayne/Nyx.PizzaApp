package space.lala.nyxpizzaapp.navdrawer.ui.orderhistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.utils.PriceFormatter;

public class ProductAdapter extends BaseAdapter {

    Product product;

    private List<Product> products = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public void setProducts(List<Product> products) {
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Product getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductAdapter.ViewHolder holder;
        inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.listview_item, parent, false);
        holder = new ProductAdapter.ViewHolder();

        holder.productName = convertView.findViewById(R.id.order_product);
        holder.quantityOfProduct = convertView.findViewById(R.id.order_quantity_of_products);
        holder.productPrice = convertView.findViewById(R.id.order_product_price);
        holder.orderSum = convertView.findViewById(R.id.order_sum);
        product = getItem(position);

        convertView.setTag(holder);

        holder.productName.setText(product.getTitle());
        holder.quantityOfProduct.setText(String.valueOf(product.getQuantityOfSelectedProduct()));
        holder.productPrice.setText(PriceFormatter.format(product.getPrice()));
        holder.orderSum.setText(convertView.getResources().getString(
                R.string.sum,
                String.valueOf(product.getPrice() * product.getQuantityOfSelectedProduct()))
        );
        return convertView;
    }

    static class ViewHolder {
        TextView productName;
        TextView quantityOfProduct;
        TextView productPrice;
        TextView orderSum;
    }
}
