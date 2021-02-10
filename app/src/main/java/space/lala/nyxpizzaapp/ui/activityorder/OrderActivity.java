package space.lala.nyxpizzaapp.ui.activityorder;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import space.lala.nyxpizzaapp.CartProductsAdapter;
import space.lala.nyxpizzaapp.R;


public class OrderActivity extends AppCompatActivity {

    CartProductsAdapter adapter;
    private OrderActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        RecyclerView cartProductsRecycler = findViewById(R.id.cart_products_recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        cartProductsRecycler.setLayoutManager(layoutManager);
        adapter = new CartProductsAdapter();
        cartProductsRecycler.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(OrderActivityViewModel.class);
        viewModel.getSelectedProducts().observe(this, cartProducts -> {
            adapter.setSelectedProducts(cartProducts);
        });
    }

    public void onClickDone(View view) {
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
}
