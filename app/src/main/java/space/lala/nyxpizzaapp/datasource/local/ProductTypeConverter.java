package space.lala.nyxpizzaapp.datasource.local;

import androidx.room.TypeConverter;

import space.lala.nyxpizzaapp.model.Product;

public class ProductTypeConverter {

    @TypeConverter
    public Integer enumToInt (Product.Type type) {
        if (type == null) {
            return null;
        } else {
            return type.ordinal();
        }
    }

    @TypeConverter
    public static Product.Type type(Integer type) {
        return type == null ? null: Product.Type.values()[type];
    }

}
