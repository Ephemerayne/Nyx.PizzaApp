package space.lala.nyxpizzaapp.ui.activityorder;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import space.lala.nyxpizzaapp.CartProductListener;
import space.lala.nyxpizzaapp.CartProductsAdapter;
import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.activitydetailproducts.ProductDetailActivity;
import space.lala.nyxpizzaapp.utils.PriceFormatter;


public class OrderActivity extends AppCompatActivity implements CartProductListener {

    private CartProductsAdapter adapter;
    private OrderActivityViewModel viewModel;
    private EditText name;
    private EditText phone;
    private EditText details;
    private TextView cartSum;
    private TextView cartQuantity;
    private TextView noSelectedProducts;
    private Button clearCartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        details = findViewById(R.id.edit_details);
        cartSum = findViewById(R.id.cart_product_sum);
        cartQuantity = findViewById(R.id.cart_product_quantity);
        noSelectedProducts = findViewById(R.id.text_no_selected_products);
        clearCartButton = findViewById(R.id.clear_cart);


        details.setImeOptions(EditorInfo.IME_ACTION_DONE);
        details.setRawInputType(InputType.TYPE_CLASS_TEXT);

        RecyclerView cartProductsRecycler = findViewById(R.id.cart_products_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cartProductsRecycler.setLayoutManager(layoutManager);
        adapter = new CartProductsAdapter(this);
        cartProductsRecycler.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(OrderActivityViewModel.class);
        viewModel.getSelectedProducts().observe(this, cartProducts -> {
            adapter.setSelectedProducts(cartProducts);
            clearCartButton.setOnClickListener(view -> clearCart(cartProducts));

            if (adapter.getItemCount() != 0) {
                noSelectedProducts.setVisibility(View.GONE);
                clearCartButton.setVisibility(View.VISIBLE);
            } else {
                noSelectedProducts.setVisibility(View.VISIBLE);
                clearCartButton.setVisibility(View.INVISIBLE);
            }

            cartSum.setText(
                    getString(
                            R.string.sum,
                            PriceFormatter.format(viewModel.sumSelectedProductsPrices(cartProducts))
                    )
            );
            cartQuantity.setText(
                    getString(
                            R.string.quantity_products,
                            viewModel.quantityOfSelectedProducts(cartProducts)
                    )
            );
        });
    }

    private void clearCart(List<Product> cartProducts) {
        for (Product product : cartProducts) {
            product.setSelected(false);
            viewModel.update(product);
        }
    }

    @Override
    public void onAddRemoveButtonsClick(Product product) {
        viewModel.update(product);
    }

    @Override
    public void onCartProductClick(Product product) {
        Intent intent = new Intent(this, ProductDetailActivity.class);
        intent.putExtra(ProductDetailActivity.EXTRA_PRODUCT_ID, product.getId());
        startActivity(intent);
    }

//    public void onClickDone(View view) {
//        CharSequence text = "Ваш заказ был отправлен!";
//        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, 10000);
//        snackbar.setAction("Посмотреть",
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Toast toast = Toast.makeText(OrderActivity.this, "Отменено!",
//                                Toast.LENGTH_LONG);
//                        toast.show();
//                    }
//                });
//        snackbar.show();
}


