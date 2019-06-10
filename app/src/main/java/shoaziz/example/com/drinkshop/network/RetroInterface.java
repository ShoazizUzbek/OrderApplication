package shoaziz.example.com.drinkshop.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import shoaziz.example.com.drinkshop.data.Comments;
import shoaziz.example.com.drinkshop.data.Food;
import shoaziz.example.com.drinkshop.data.Login;
import shoaziz.example.com.drinkshop.data.Menu;
import shoaziz.example.com.drinkshop.data.PostComment;
import shoaziz.example.com.drinkshop.data.PostReq;
import shoaziz.example.com.drinkshop.data.Rate;
import shoaziz.example.com.drinkshop.data.Registration;

public interface RetroInterface {
    @POST("order")
    Call<PostReq> postReq(@Header("Authorization")String token, @Body PostReq postReq);

    @GET("menu")
    Call<List<Menu>> getMenu(@Header("Authorization") String token, @Query("restaurant_id")String res_id);

    @GET("food")
    Call<List<Food>> getFood(@Header("Authorization") String token, @Query("menu_id")String res_id);

    @POST("rate")
    Call<Rate> rateUs(@Header("Authorization")String token,@Body Rate rate);

    @POST("comment")
    Call<PostComment> sendComment(@Header("Authorization")String token,@Body PostComment postComment);

    @POST("register")
    Call<Registration> register(@Body Registration registration);

//    @POST("register")
//    Call<Login> register(@Body Registration registration);

    @GET("comment")
    Call<List<Comments>> getComments(@Header("Authorization")String token,@Query("food_id")String s);



}
