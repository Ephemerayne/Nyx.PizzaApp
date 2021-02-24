package space.lala.nyxpizzaapp.datasource.local.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import space.lala.nyxpizzaapp.model.UserOrder;

@Database(entities = {UserOrder.class}, version = 1)

public abstract class UserOrdersDatabase extends RoomDatabase {

    private static UserOrdersDatabase instance;

    public abstract OrderDao orderDao();

    public static synchronized UserOrdersDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    UserOrdersDatabase.class, "user_orders")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
