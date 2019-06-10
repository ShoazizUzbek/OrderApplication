package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Food implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;

    public Food() {
    }



    @SerializedName("restaurant_id")
    @Expose
    private String restaurantId;
    @SerializedName("menu_id")
    @Expose
    private String menuId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("cooking_time")
    @Expose
    private String cookingTime;

    @SerializedName("rating")
    Rating rating;

    public Food(String name, String image,String price,String cookingTime){
        this.name = name;
        this.image = image;
        this.price = price;
        this.cookingTime = cookingTime;
    }
    public Food(String id, String restaurantId, String menuId, String name, String description, String image, String price, String cookingTime, Rating rating, List<Comments> comments) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.menuId = menuId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.cookingTime = cookingTime;
        this.rating = rating;
        this.comments = comments;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public Rating getRating() {
        return rating;
    }
    @SerializedName("comments")
    List<Comments> comments;

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(String cookingTime) {
        this.cookingTime = cookingTime;
    }

}

