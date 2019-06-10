package shoaziz.example.com.drinkshop.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Registration {

    @SerializedName("student_id")
    @Expose
    private String studentId;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("password")
    @Expose
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public Registration(String studentId, String gender, String phone, String password) {
        this.studentId = studentId;
        this.gender = gender;
        this.phone = phone;
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
