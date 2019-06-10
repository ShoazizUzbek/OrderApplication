package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Rating implements Serializable {
    @SerializedName("rate")
    String rate;
    @SerializedName("count")
    String count;

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public Rating(String rate, String count) {
        this.rate = rate;
        this.count = count;
    }
}
