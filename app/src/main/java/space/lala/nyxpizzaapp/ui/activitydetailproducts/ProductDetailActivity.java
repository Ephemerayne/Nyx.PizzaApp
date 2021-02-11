package space.lala.nyxpizzaapp.ui.activitydetailproducts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.BaseActivity;
import space.lala.nyxpizzaapp.ui.activityorder.OrderActivity;
import space.lala.nyxpizzaapp.utils.PriceFormatter;

public class ProductDetailActivity extends BaseActivity {

    private TextView productName;
    private TextView productPrice;
    private TextView productDescription;
    private ImageView productImage;
    private Button addProductButton;
    private Toolbar toolbar;
    private ProductDetailActivityViewModel viewModel;
    private LinearLayout layout;
    private Product transferProduct;
    private int addRemoveSnackBarMsg;

    public static final String EXTRA_PRODUCT_ID = "productId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        int productId = (Integer) getIntent().getExtras().get(EXTRA_PRODUCT_ID);

        productName = findViewById(R.id.product_text);
        productPrice = findViewById(R.id.product_price);
        productDescription = findViewById(R.id.product_description);
        addProductButton = findViewById(R.id.add_product_to_buy_button);
        productImage = findViewById(R.id.product_image);
        layout = findViewById(R.id.layout);

        setViewModel(productId);
        setAddProductButton(transferProduct);
        transferProductToCart(productId);
    }

    private void setViewModel(int productId) {
        viewModel = ViewModelProviders.of(this).get(ProductDetailActivityViewModel.class);
        viewModel.getProduct(productId).observe(this, product -> {
            if (product != null) {
                productName.setText(product.getTitle());
                productPrice.setText(
                        getString(R.string.prices, PriceFormatter.format(product.getPrice()))
                );
                productDescription.setText(product.getDescription());
                setProductImage(product);
                setToolbarTitle(product.getType());

                if (product.isSelected()) {
                    addProductButton.setText(R.string.button_remove_product);
                    addRemoveSnackBarMsg = R.string.snackbar_remove_product;
                } else {
                    addProductButton.setText(R.string.button_add_product);
                    addRemoveSnackBarMsg = R.string.snackbar_add_product;
                }
            }
        });
    }

    private void transferProductToCart(int productId) {
        viewModel.getProductSingle(productId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Product>() {
                    @Override
                    public void onSuccess(@NonNull Product product) {
                        transferProduct = product;
                        setAddProductButton(transferProduct);
                        product.setQuantityOfSelectedProduct(1);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }

    private void setToolbarTitle(Product.Type productType) {
        switch (productType) {
            case Pizza:
                toolbar.setTitle(getString(R.string.pizza_tab));

                break;
            case Pasta:
                toolbar.setTitle(getString(R.string.pasta_tab));
                break;
            case Drinks:
                toolbar.setTitle(getString(R.string.drinks_tab));
                break;
        }
    }

    private void setProductImage(Product product) {
        Picasso.with(this)
                .load(product.getImageURL())
                .into(productImage, new Callback.EmptyCallback() {
                    @Override
                    public void onSuccess() {
                        super.onSuccess();
                    }

                    @Override
                    public void onError() {
                        super.onError();
                        productImage.setImageDrawable(
                                ContextCompat.getDrawable(
                                        ProductDetailActivity.this,
                                        R.drawable.pizza_error_loading
                                )
                        );
                    }
                });
    }

    private void setAddProductButton(Product transferProduct) {
        addProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar snackbar = Snackbar.make(layout, addRemoveSnackBarMsg, 5000);
                snackbar.setAction("Посмотреть заказ",
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(ProductDetailActivity.this, OrderActivity.class);
                                startActivity(intent);
                            }
                        });
                snackbar.show();

                if (transferProduct != null) {
                    transferProduct.setSelected(!transferProduct.isSelected());
                    viewModel.update(transferProduct);
                }
            }
        });
    }
}

