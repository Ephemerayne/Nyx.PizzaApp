package space.lala.nyxpizzaapp.ui.activityorder;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;

import java.time.LocalDateTime;
import java.util.List;

import space.lala.nyxpizzaapp.CartProductListener;
import space.lala.nyxpizzaapp.CartProductsAdapter;
import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.model.UserOrder;
import space.lala.nyxpizzaapp.ui.activitydetailproducts.ProductDetailActivity;
import space.lala.nyxpizzaapp.ui.activitylogin.LoginActivity;
import space.lala.nyxpizzaapp.utils.PriceFormatter;


public class OrderActivity extends AppCompatActivity implements CartProductListener {

    private CartProductsAdapter adapter;
    private OrderActivityViewModel viewModel;
    private EditText name;
    private EditText phone;
    private EditText details;
    private TextView cartSum;
    private TextView cartQuantity;
    private CardView cardViewNoSelectedProducts;
    private TextView noSelectedProducts;
    private Button clearCartButton;
    private FirebaseAuth auth;
    private List<Product> cartProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();

        name = findViewById(R.id.edit_name);
        phone = findViewById(R.id.edit_phone);
        details = findViewById(R.id.edit_details);
        cartSum = findViewById(R.id.cart_product_sum);
        cartQuantity = findViewById(R.id.cart_product_quantity);
        cardViewNoSelectedProducts = findViewById(R.id.card_view_no_selected);
        noSelectedProducts = findViewById(R.id.text_no_selected_products);
        clearCartButton = findViewById(R.id.clear_cart);

        if (auth.getCurrentUser() != null) {
            phone.setText(auth.getCurrentUser().getPhoneNumber());
        }

        details.setImeOptions(EditorInfo.IME_ACTION_DONE);
        details.setRawInputType(InputType.TYPE_CLASS_TEXT);

        RecyclerView cartProductsRecycler = findViewById(R.id.cart_products_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cartProductsRecycler.setLayoutManager(layoutManager);
        adapter = new CartProductsAdapter(this);
        cartProductsRecycler.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(OrderActivityViewModel.class);
        viewModel.getSelectedProducts().observe(this, cartProducts -> {
            this.cartProducts = cartProducts;
            adapter.setSelectedProducts(cartProducts);
            clearCartButton.setOnClickListener(view -> clearCart(cartProducts));

            if (adapter.getItemCount() != 0) {
                cardViewNoSelectedProducts.setVisibility(View.GONE);
                noSelectedProducts.setVisibility(View.GONE);
                clearCartButton.setVisibility(View.VISIBLE);
            } else {
                cardViewNoSelectedProducts.setVisibility(View.VISIBLE);
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

    public void onClickDone(View view) {
        if (auth.getCurrentUser() != null) {
            CharSequence text = "Ваш заказ был отправлен!";
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
            UserOrder userOrder = new UserOrder(
                    auth.getCurrentUser().getUid(),
                    LocalDateTime.now(),
                    cartProducts
            );
            viewModel.insertOrder(userOrder);
        } else {
            CharSequence text = "Для продолжения зарегистрируйтесь";
            Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, 10000);
            snackbar.setAction("Зарегистрироваться",
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(OrderActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    });
            snackbar.show();
        }
    }
}


