package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrderItem {


    @SerializedName("food_id")
    @Expose
    String food_id;
    @SerializedName("quantity")
    @Expose
    String  quantity;

    public OrderItem(String food_id, String quantity) {
        this.food_id = food_id;
        this.quantity = quantity;
    }

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

}
