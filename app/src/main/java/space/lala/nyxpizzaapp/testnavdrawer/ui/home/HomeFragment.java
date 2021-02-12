package space.lala.nyxpizzaapp.testnavdrawer.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import space.lala.nyxpizzaapp.R;
import space.lala.nyxpizzaapp.model.Product;
import space.lala.nyxpizzaapp.ui.fragmentmain.MainFragment;
import space.lala.nyxpizzaapp.ui.fragmentpizzas.ProductFragment;


public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SectionsPagerAdapter pagerAdapter = new SectionsPagerAdapter(getActivity());
        ViewPager2 pager = root.findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        TabLayout tabLayout = root.findViewById(R.id.tabs);

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
        return root;
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