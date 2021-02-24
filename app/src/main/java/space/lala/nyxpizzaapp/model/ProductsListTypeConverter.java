package space.lala.nyxpizzaapp.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class ProductsListTypeConverter {

    @TypeConverter
    public String fromProductsList(List<Product> productsList) {
        if (productsList == null) {
            return null;
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Product>>() {}.getType();
            String json = gson.toJson(productsList, type);
            return json;
        }
    }

    @TypeConverter
    public List<Product> toProductsList(String productsListJson) {
        if (productsListJson == null) {
            return null;
        } else {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Product>>() {}.getType();
            List<Product> productsList = gson.fromJson(productsListJson, type);
            return productsList;
        }
    }
}
