package space.lala.nyxpizzaapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class PastaDetailActivity extends AppCompatActivity {

    public static final String EXTRA_PASTA_ID = "pastaId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pasta_detail);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

        int pastaId = (Integer) getIntent().getExtras().get(EXTRA_PASTA_ID);
        String pastaName = Pasta.pastas.get(pastaId).getName();
        TextView title = findViewById(R.id.pasta_text);
        title.setText(pastaName);

        int pastaPrice = Pasta.pastas.get(pastaId).getPrice();
        TextView price = findViewById(R.id.pasta_price);
        price.setText(getString(R.string.prices, pastaPrice));

        int pastaImage = Pasta.pastas.get(pastaId).getImageResourceId();
        ImageView imageView = findViewById(R.id.pasta_image);
        imageView.setImageDrawable(ContextCompat.getDrawable(this, pastaImage));
        imageView.setContentDescription(pastaName);
    }
}