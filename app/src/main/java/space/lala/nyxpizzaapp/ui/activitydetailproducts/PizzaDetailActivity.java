package space.lala.nyxpizzaapp.ui.activitydetailproducts;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import space.lala.nyxpizzaapp.R;

public class PizzaDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PIZZA_ID = "pizzaId";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_detail);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        int pizzaId = (Integer) getIntent().getExtras().get(EXTRA_PIZZA_ID);
//        String pizzaName = Pizza.pizzas.get(pizzaId).getName();
        TextView title = findViewById(R.id.pizza_text);
//        title.setText(pizzaName);

//        int pizzaPrice = Pizza.pizzas.get(pizzaId).getPrice();
        TextView price = findViewById(R.id.pizza_price);
//        price.setText(getString(R.string.prices, pizzaPrice));

//        String pizzaImage = Pizza.pizzas.get(pizzaId).getImageURL();
        ImageView imageView = findViewById(R.id.pizza_image);
//        imageView.setImageDrawable(ContextCompat.getDrawable(this, pizzaImage));
//        imageView.setContentDescription(pizzaName);
    }
}
