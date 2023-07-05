
package DataBase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Productdatum {

    @SerializedName("ID")
    @Expose
    private String id;
    @SerializedName("SELLERID")
    @Expose
    private String sellerid;
    @SerializedName("NAME")
    @Expose
    private String name;
    @SerializedName("STOCK")
    @Expose
    private String stock;
    @SerializedName("PRICE")
    @Expose
    private String price;
    @SerializedName("CATEGORY")
    @Expose
    private String category;
    @SerializedName("IMAGE")
    @Expose
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
