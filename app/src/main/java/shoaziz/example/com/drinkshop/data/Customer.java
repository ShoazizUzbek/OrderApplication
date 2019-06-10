package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("avatar")
    String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Customer(String id, String avatar) {
        this.id = id;
        this.avatar = avatar;
    }
}
