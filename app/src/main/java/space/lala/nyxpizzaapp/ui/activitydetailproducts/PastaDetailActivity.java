package space.lala.nyxpizzaapp.ui.activitydetailproducts;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import space.lala.nyxpizzaapp.R;

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
    }
}