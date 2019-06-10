package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.SerializedName;

public class PostComment {
    @SerializedName("food_id")
    String food_id;
    @SerializedName("text")
    String text;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public PostComment(String food_id, String text) {
        this.food_id = food_id;
        this.text = text;
    }
}
