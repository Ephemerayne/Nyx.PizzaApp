package space.lala.nyxpizzaapp;

import androidx.room.TypeConverter;

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
