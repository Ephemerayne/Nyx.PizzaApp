package space.lala.nyxpizzaapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "users_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String userName;
    private final String userPhone;
    private final String userPassword;
    private final String userEmail;
    private final boolean isSubscribed;

    public User(
            String userName,
            String userPhone,
            String userPassword,
            String userEmail,
            boolean isSubscribed
    ) {
        this.userName = userName;
        this.userPhone = userPhone;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
        this.isSubscribed = isSubscribed;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }
}
