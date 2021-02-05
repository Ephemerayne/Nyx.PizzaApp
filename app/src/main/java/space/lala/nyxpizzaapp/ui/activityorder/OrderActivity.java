package space.lala.nyxpizzaapp.ui.activityorder;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import space.lala.nyxpizzaapp.R;


public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    public void onClickDone(View view) {
        CharSequence text = "Ваш заказ был отправлен!";
        Snackbar snackbar = Snackbar.make(findViewById(R.id.coordinator), text, 10000);
        snackbar.setAction("Посмотреть",
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast toast = Toast.makeText(OrderActivity.this, "Отменено!",
                                Toast.LENGTH_LONG);
                        toast.show();
                    }
                });
        snackbar.show();
    }
}
