package shoaziz.example.com.drinkshop;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shoaziz.example.com.drinkshop.activities.SignInActivity;
import shoaziz.example.com.drinkshop.data.OrderItem;
import shoaziz.example.com.drinkshop.data.PostReq;
import shoaziz.example.com.drinkshop.network.RetroInterface;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    LinearLayout  linearLayout;
    PageIndecator pageIndecator;
    TextView dots[];
    Button button;
    AppCompatRatingBar appCompatRatingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        button = findViewById(R.id.btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
        viewPager = findViewById(R.id.viewpager);
        linearLayout = findViewById(R.id.linearLayout);
        pageIndecator = new PageIndecator(this);
        viewPager.setAdapter(pageIndecator);
        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);
    }
    void json(){
//        OkHttpClient.Builder client = new OkHttpClient.Builder();
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        client.addInterceptor(loggingInterceptor);
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//      //  GsonConverterFactory.create(gson);
//        Retrofit.Builder builder=  new Retrofit.Builder()
//                .baseUrl("http://www.ilmsoft.uz:8000/api/")
//                .client(client.build())
//                .addConverterFactory(GsonConverterFactory.create(gson));
//        Retrofit retrofit = builder.build();
//        RetroInterface retroInterface = retrofit.create(RetroInterface.class);
//
//        List<OrderItem> orderItems = new ArrayList<>();
//        orderItems.add(new OrderItem(1,9));
//        Gson gson1 = new Gson();
//        PostReq newIns = new PostReq(1,orderItems);
//        String j = gson1.toJson(newIns);
//
//
//
//        //Call<PostReq> call = retroInterface.postReq("Token 14a79f19e5ff301e36bf8799993d370c4f3fbdf6",new PostReq("1",orderItems));
//        Call<PostReq> call = retroInterface.postReq("Token 14a79f19e5ff301e36bf8799993d370c4f3fbdf6", new PostReq(1,orderItems));
//        Log.d("jsonnn",call.toString());
//
//        Log.d("newjson",j);
//        call.enqueue(new Callback<PostReq>() {
//            @Override
//            public void onResponse(Call<PostReq> call, Response<PostReq> response) {
//                if (response.isSuccessful()){
//                    Toast.makeText(MainActivity.this, "suc", Toast.LENGTH_SHORT).show();
//                    Log.d("aaa",response.toString());
//                }else {
//                    Log.d("sss",response.toString());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<PostReq> call, Throwable t) {
//                Log.d("tizimda xatolik",t.getMessage());
//                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }
    void addDots(int position){
        dots = new TextView[3];
        linearLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.trans));
            linearLayout.addView(dots[i]);
        }
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.main_color));
        }
    }
    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            addDots(i);
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };
}
