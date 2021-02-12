package space.lala.nyxpizzaapp.ui;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import ru.nikartm.support.ImageBadgeView;
import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.ui.activityorder.OrderActivity;

public abstract class BaseActivity extends AppCompatActivity {

    protected ImageBadgeView imageBadgeView;
    private BaseActivityViewModel viewModel;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        imageBadgeView = (ImageBadgeView) menu.findItem(R.id.action_add_product_in_basket).getActionView();
        viewModel = ViewModelProviders.of(this).get(BaseActivityViewModel.class);
        viewModel.getSelectedProducts().observe(this, products -> {
            imageBadgeView.setBadgeValue(products.size());
        });
        setCartClickListener(menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void setCartClickListener(Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem item = menu.getItem(i);
            if (item.getItemId() == R.id.action_add_product_in_basket) {
                imageBadgeView = (ImageBadgeView) item.getActionView();
                if (imageBadgeView != null) {
                    imageBadgeView.setOnClickListener(view -> {
                        Intent intent = new Intent(this, OrderActivity.class);
                        startActivity(intent);
                    });
                }
            }
        }
    }
}