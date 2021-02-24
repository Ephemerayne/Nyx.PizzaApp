package space.lala.nyxpizzaapp.datasource.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

import space.lala.nyxpizzaapp.datasource.local.database.OrderDao;
import space.lala.nyxpizzaapp.datasource.local.database.UserOrdersDatabase;
import space.lala.nyxpizzaapp.model.UserOrder;

public class UserOrdersRepository {
    private OrderDao orderDao;

    public UserOrdersRepository(Application application) {
        UserOrdersDatabase database = UserOrdersDatabase.getInstance(application);
        orderDao = database.orderDao();
    }

    public void insertUserOrders(UserOrder userOrder) {
        new InsertUserOrdersAsyncTask(orderDao).execute(userOrder);
    }

    public static class InsertUserOrdersAsyncTask extends AsyncTask<UserOrder, Void, Void> {
        private OrderDao orderDao;

        private InsertUserOrdersAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(UserOrder... userOrders) {
            orderDao.insert(userOrders[0]);
            return null;
        }
    }

    public void deleteUserOrders(UserOrder userOrder) {
        new DeleteUserOrdersAsyncTask(orderDao).execute(userOrder);
    }

    private static class DeleteUserOrdersAsyncTask extends AsyncTask<UserOrder, Void, Void> {
        private OrderDao orderDao;

        private DeleteUserOrdersAsyncTask(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(UserOrder... userOrders) {
            orderDao.deleteOrders(userOrders[0].getUserUid());
            return null;
        }
    }

    public LiveData<List<UserOrder>> getUserOrders(String userUid) {
        return orderDao.getUserOrders(userUid);
    }
}
