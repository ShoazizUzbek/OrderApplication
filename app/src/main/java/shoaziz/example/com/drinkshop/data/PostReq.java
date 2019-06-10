package shoaziz.example.com.drinkshop.data;

import android.content.IntentSender;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PostReq {
    @SerializedName("restaurant_id")
    @Expose
    String restaurant_id;

    @SerializedName("comment")
    String comment;

    @SerializedName("order_items")
    @Expose
    List<OrderItem>  order_items;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PostReq(String comment, String restaurant_id, List<OrderItem> reqFoods) {
        this.comment = comment;
        this.restaurant_id = restaurant_id;
        this.order_items = reqFoods;
    }

    public String getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(String restaurant_id) {
        this.restaurant_id = restaurant_id;
    }

    public List<OrderItem> getOrder_items() {
        return order_items;
    }

    public void setOrder_items(List<OrderItem> order_items) {
        this.order_items = order_items;
    }
}
