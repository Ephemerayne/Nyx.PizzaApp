package space.lala.nyxpizzaapp.datasource.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import space.lala.nyxpizzaapp.model.User;

public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM users_table WHERE id=:id")
    LiveData<User> getUser(int id);

    @Update
    void update(User user);
}
