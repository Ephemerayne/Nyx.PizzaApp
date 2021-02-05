package space.lala.nyxpizzaapp.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import space.lala.nyxpizzaapp.Product;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Query("DELETE FROM product_table WHERE id=:id")
    void deleteProduct(int id);

    @Query("SELECT * FROM product_table WHERE type = :typeOrdinal")
    LiveData<List<Product>> getAllProducts(int typeOrdinal);

    @Query("SELECT * FROM product_table WHERE type = :typeOrdinal")
   List<Product> getAllProductsSync(int typeOrdinal);

    @Query("SELECT * FROM product_table WHERE id=:id LIMIT 1")
    LiveData<List<Product>> getProduct(int id);
}
