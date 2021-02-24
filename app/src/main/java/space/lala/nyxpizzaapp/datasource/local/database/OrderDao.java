package space.lala.nyxpizzaapp.datasource.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import space.lala.nyxpizzaapp.model.UserOrder;

@Dao
public interface OrderDao {

    @Insert
    void insert(UserOrder userOrder);

    @Query("DELETE FROM user_orders WHERE userUid=:userUid")
    void deleteOrders(String userUid);

    @Query("SELECT * FROM user_orders WHERE userUid=:userUid")
    LiveData<List<UserOrder>> getUserOrders(String userUid);
}
