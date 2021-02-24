package space.lala.nyxpizzaapp.navdrawer.ui.orderhistory;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import space.lala.nyxpizzaapp.datasource.repository.UserOrdersRepository;
import space.lala.nyxpizzaapp.model.UserOrder;

public class OrderHistoryFragmentViewModel extends AndroidViewModel {
    private UserOrdersRepository repository;

    public OrderHistoryFragmentViewModel(@NonNull Application application) {
        super(application);
        repository = new UserOrdersRepository(application);
    }

    public LiveData<List<UserOrder>> getUserOrders(String userUid) {
        return repository.getUserOrders(userUid);
    }
}
