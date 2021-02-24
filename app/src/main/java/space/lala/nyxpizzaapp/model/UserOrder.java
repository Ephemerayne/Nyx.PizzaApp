package space.lala.nyxpizzaapp.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.time.LocalDateTime;
import java.util.List;

@Entity(tableName = "user_orders")
@TypeConverters({DateTimeConverter.class, ProductsListTypeConverter.class})
public class UserOrder {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private final String userUid;
    private final LocalDateTime dateTimeOrder;
    private final List<Product> products;

    public UserOrder(String userUid, LocalDateTime dateTimeOrder, List<Product> products) {
        this.userUid = userUid;
        this.dateTimeOrder = dateTimeOrder;
        this.products = products;
    }

    public int getId() {
        return id;
    }

    public String getUserUid() {
        return userUid;
    }

    public LocalDateTime getDateTimeOrder() {
        return dateTimeOrder;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setId(int id) {
        this.id = id;
    }
}
