package space.lala.nyxpizzaapp.ui.activitymain;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.BaseActivity;
import space.lala.nyxpizzaapp.ui.fragmentmain.MainFragment;
import space.lala.nyxpizzaapp.ui.fragmentpizzas.ProductFragment;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(this);
        ViewPager2 pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);

        new TabLayoutMediator(tabLayout, pager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText(R.string.home_tab);
                            break;
                        case 1:
                            tab.setText(R.string.pizza_tab);
                            break;
                        case 2:
                            tab.setText(R.string.pasta_tab);
                            break;
                        case 3:
                            tab.setText(R.string.drinks_tab);
                            break;
                    }
                }
        ).attach();
    }

    private class SectionsPagerAdapter extends FragmentStateAdapter {

        public SectionsPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new MainFragment();
                case 1:
                    return ProductFragment.newInstance(Product.Type.Pizza.ordinal());
                case 2:
                    return ProductFragment.newInstance(Product.Type.Pasta.ordinal());
                case 3:
                    return ProductFragment.newInstance(Product.Type.Drinks.ordinal());
            }
            return null;
        }

        @Override
        public int getItemCount() {
            return 4;
        }
    }
}