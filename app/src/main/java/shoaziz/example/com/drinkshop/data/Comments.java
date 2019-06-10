package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Comments implements Serializable {
    @SerializedName("id")
    String id;
    @SerializedName("text")
    String text;
    @SerializedName("date")
    String dat;
    @SerializedName("customer")
    Customer customer;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDat() {
        return dat;
    }

    public void setDat(String dat) {
        this.dat = dat;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Comments(String id, String text, String dat, Customer customer) {
        this.id = id;
        this.text = text;
        this.dat = dat;
        this.customer = customer;
    }
}
