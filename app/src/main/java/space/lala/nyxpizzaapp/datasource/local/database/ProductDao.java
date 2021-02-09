package space.lala.nyxpizzaapp.datasource.local.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import space.lala.nyxpizzaapp.model.Product;

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
    LiveData<Product> getProduct(int id);

    @Update
    void update(Product product);

    @Query("SELECT * FROM product_table WHERE id=:id LIMIT 1")
    Single<Product> getProductSingle(int id);

    @Query("SELECT * FROM product_table WHERE isSelected = 1")
    LiveData<List<Product>> getSelectedProducts();
}
