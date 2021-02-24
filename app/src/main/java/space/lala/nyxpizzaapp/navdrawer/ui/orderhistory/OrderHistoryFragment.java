package space.lala.nyxpizzaapp.navdrawer.ui.orderhistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import space.lala.nyxpizzaapp.R;

public class OrderHistoryFragment extends Fragment {

    private OrderHistoryFragmentViewModel viewModel;
    private FirebaseAuth auth;
    private RecyclerView allOrdersRecyclerView;
    private OrdersRecyclerAdapter adapter = new OrdersRecyclerAdapter();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_history, container, false);
        auth = FirebaseAuth.getInstance();

        allOrdersRecyclerView = view.findViewById(R.id.user_order_history);
        allOrdersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        allOrdersRecyclerView.setAdapter(adapter);

        viewModel = ViewModelProviders.of(this).get(OrderHistoryFragmentViewModel.class);

        if (auth.getCurrentUser() != null) {
            viewModel.getUserOrders(
                    auth.getCurrentUser().getUid()).observe(getViewLifecycleOwner(), userOrders -> {
                adapter.setUserOrders(userOrders);
            });

        }
        return view;
    }
}

