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

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_product_in_basket:
                Intent intent = new Intent(this, OrderActivity.class);
                startActivity(intent);
                return true;
            default:
                onBackPressed();
                return true;
        }
    }

//    public void setBasketValue(int value) {
//        imageBadgeView.setBadgeValue(value);
//    }
}
