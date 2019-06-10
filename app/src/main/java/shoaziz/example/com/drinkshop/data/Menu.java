package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Menu {



        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("restaurant_id")
        @Expose
        private String restaurantId;
        @SerializedName("image")
        @Expose
        private String image;

        @SerializedName("name")
        private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Menu(String id, String restaurantId, String image, String name) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.image = image;
        this.name = name;
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }


}
