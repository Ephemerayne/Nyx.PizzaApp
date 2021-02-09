package space.lala.nyxpizzaapp.ui.activitydetailproducts;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.BaseActivity;
import space.lala.nyxpizzaapp.utils.PriceFormatter;

public class ProductDetailActivity extends BaseActivity {

    private TextView productName;
    private TextView productPrice;
    private TextView productDescription;
    private ImageView productImage;
    private Button addProductButton;
    private Toolbar toolbar;

    private ProductActivityViewModel viewModel;
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

        viewModel = ViewModelProviders.of(this).get(ProductActivityViewModel.class);
        viewModel.getProduct(productId).observe(this, product -> {
            if (product != null) {
                productName.setText(product.getTitle());
                productPrice.setText(
                        getString(R.string.prices, PriceFormatter.format(product.getPrice()))
                );
                productDescription.setText(product.getDescription());
                setProductImage(product);
                setToolbarTitle(product.getType());
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

}
