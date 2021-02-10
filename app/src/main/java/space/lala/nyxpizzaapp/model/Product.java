package space.lala.nyxpizzaapp.model;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.SerializedName;

import space.lala.nyxpizzaapp.datasource.local.ProductTypeConverter;

@Entity(tableName = "product_table", indices = {@Index(value = {"idFromServer"},
        unique = true)})
@TypeConverters(ProductTypeConverter.class)

public class Product {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("id_from_server")
    private int idFromServer;

    @SerializedName("name")
    private final String title;
    private final String description;
    private final double price;
    private final Type type;
    private boolean isSelected = false;
    private int quantityOfSelectedProduct = 0;

    @SerializedName("image")
    private final String imageURL;

    public Product(String title, String description, double price, Type type, String imageURL) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.type = type;
        this.imageURL = imageURL;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + idFromServer;
        result = 31 * result + title.hashCode();
//        result = 31 * result + description.hashCode();
        result = 31 * result + (int) price;
        result = 31 * result + type.hashCode();
        result = 31 * result + imageURL.hashCode();
        return result;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == this) return true;
        if (!(obj instanceof Product)) return false;

        Product product = (Product) obj;

        return product.idFromServer == idFromServer &&
                product.title.equals(title) &&
//                product.description.equals(description) &&
                product.price == price &&
                product.type == type &&
                product.imageURL.equals(imageURL);

    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getQuantityOfSelectedProduct() {
        return quantityOfSelectedProduct;
    }

    public void setQuantityOfSelectedProduct(int quantityOfSelectedProduct) {
        this.quantityOfSelectedProduct = quantityOfSelectedProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getIdFromServer() {
        return idFromServer;
    }

    public void setIdFromServer(int idFromServer) {
        this.idFromServer = idFromServer;
    }

    public enum Type {
        @SerializedName("0")
        Pizza,
        @SerializedName("1")
        Pasta,
        @SerializedName("2")
        Drinks
    }
}
