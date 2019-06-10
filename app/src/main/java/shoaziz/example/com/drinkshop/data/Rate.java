package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.SerializedName;

public class Rate {
    @SerializedName("food_id")
    String food_id;

    @SerializedName("rate")
    Float rate;

    public String getFood_id() {
        return food_id;
    }

    public void setFood_id(String food_id) {
        this.food_id = food_id;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }

    public Rate(String food_id, Float rate) {
        this.food_id = food_id;
        this.rate = rate;
    }
}
