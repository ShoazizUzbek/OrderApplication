package shoaziz.example.com.drinkshop.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shoaziz.example.com.drinkshop.Adapters.CommentAdapter;
import shoaziz.example.com.drinkshop.Interfaces.AdapterCallback;
import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.ViewModel.Order;
import shoaziz.example.com.drinkshop.ViewModel.OrderViewModel;
import shoaziz.example.com.drinkshop.data.Comments;
import shoaziz.example.com.drinkshop.data.Food;
import shoaziz.example.com.drinkshop.data.PostComment;
import shoaziz.example.com.drinkshop.data.Rate;
import shoaziz.example.com.drinkshop.fragments.HomeFragment;
import shoaziz.example.com.drinkshop.fragments.ItemListDialogFragment;
import shoaziz.example.com.drinkshop.fragments.OrderFragment;
import shoaziz.example.com.drinkshop.fragments.ProfileFragment;
import shoaziz.example.com.drinkshop.network.RetroApi;
import shoaziz.example.com.drinkshop.network.RetroInterface;

public class DetailActivity extends AppCompatActivity implements AdapterCallback {

    String id ="";
    String name="" ;
    String des ="";
    String time="";
    String price ="";
    String img="" ;
    ImageView imageView;
    String quantity = "1";
    OrderViewModel orderViewModel;
    ElegantNumberButton elegantNumberButton;
    BottomNavigationView bottomNavigationView;
    CoordinatorLayout constraintLayout;
    AppCompatRatingBar appCompatRatingBar;
    RecyclerView  recyclerView;
    Food food;
    EditText msg;
    ImageButton send_btn;
    List<Comments>  commentsList;
    CommentAdapter commentAdapter;
    String last_price = "15000";
    ImageView foodImg;
    Toolbar  toolbar;
    TextView destext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        destext = findViewById(R.id.des_det);
        foodImg = findViewById(R.id.foodImg);
        toolbar = findViewById(R.id.tool_detail);
        recyclerView = findViewById(R.id.recycler_comment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        msg = findViewById(R.id.message_input);
        send_btn = findViewById(R.id.send_button);


        appCompatRatingBar = findViewById(R.id.rate_detail);
        bottomNavigationView = findViewById(R.id.navigation_detail);
        constraintLayout = findViewById(R.id.det_ac);
        elegantNumberButton = findViewById(R.id.number_button);
        elegantNumberButton.setOnValueChangeListener(new ElegantNumberButton.OnValueChangeListener() {
          @Override
          public void onValueChange(ElegantNumberButton view, int oldValue, int newValue) {
              quantity = String.valueOf(newValue);
          }
        });
        appCompatRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateUs(rating);
            }
        });
                if (getIntent()!=null){
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            des = getIntent().getStringExtra("des");
            price = getIntent().getStringExtra("time");
            time = getIntent().getStringExtra("price");
            img = getIntent().getStringExtra("img");

        }
        orderViewModel = ViewModelProviders.of(DetailActivity.this).get(OrderViewModel.class);

        imageView  = findViewById(R.id.fab_det);
        imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(DetailActivity.this, quantity+"", Toast.LENGTH_SHORT).show();
                        orderViewModel.insertAll(new Order(id,name,quantity,price));
                    }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        if (getIntent()!=null){
            food = (Food) getIntent().getSerializableExtra("alldata");
        }
        jsonComment();
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sms = msg.getText().toString();
                if (sms!=null){
                    send(sms);
                }
            }

        });


//        Glide.with(this).load(img).into(foodImg);
        if (name!=null){
            toolbar.setTitle(name);
        }
        if (des!=null){
           // destext.setText(des);
        }

    }

    private void jsonComment() {
        RetroInterface retroInterface = RetroApi.getApiClient().create(RetroInterface.class);
        Call<List<Comments>> commentCall = retroInterface.getComments("Token ffe20ab28f326136d4f701d8aa8b48dba483fe4a",id);
        commentCall.enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {

                commentsList = response.body();

                commentAdapter = new CommentAdapter(DetailActivity.this,commentsList);
                recyclerView.setAdapter(commentAdapter);
            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {

            }
        });
    }

    private void send(String sms) {
        RetroInterface retroInterface = RetroApi.getApiClient().create(RetroInterface.class);
        Call<PostComment> rateCall = retroInterface.sendComment("Token ffe20ab28f326136d4f701d8aa8b48dba483fe4a",new PostComment(id,sms));
        rateCall.enqueue(new Callback<PostComment>() {
            @Override
            public void onResponse(Call<PostComment> call, Response<PostComment> response) {
//                Snackbar snackbar = Snackbar
//                        .make(constraintLayout, "Thank you for commenting", Snackbar.LENGTH_LONG);
//
//                snackbar.show();
                Toast.makeText(DetailActivity.this, "Thank you for commenting", Toast.LENGTH_SHORT).show();
                msg.setText("");
                jsonComment();
            }

            @Override
            public void onFailure(Call<PostComment> call, Throwable t) {
//                Snackbar snackbar = Snackbar
//                        .make(constraintLayout, "Problem with connection", Snackbar.LENGTH_LONG);
//
//                snackbar.show();
                Toast.makeText(DetailActivity.this, "Problem with connection", Toast.LENGTH_SHORT).show();
                msg.setText("");
            }
        });

    }

    private void rateUs(float rating) {
        RetroInterface retroInterface = RetroApi.getApiClient().create(RetroInterface.class);
        Call<Rate> rateCall = retroInterface.rateUs("Token ffe20ab28f326136d4f701d8aa8b48dba483fe4a",new Rate(id,rating));
        rateCall.enqueue(new Callback<Rate>() {
            @Override
            public void onResponse(Call<Rate> call, Response<Rate> response) {
//                Snackbar snackbar = Snackbar
//                        .make(constraintLayout, "Thank you for rating", Snackbar.LENGTH_LONG);
//
//                snackbar.show();
                Toast.makeText(DetailActivity.this, "Thank you for rating", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Rate> call, Throwable t) {
                Toast.makeText(DetailActivity.this, "Problem with connection", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_shop:
                    Intent intent = new Intent(DetailActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.navigation_gifts:
                    ItemListDialogFragment bottomSheetFragment = new ItemListDialogFragment();
                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                    return true;
                case R.id.navigation_cart:

                    return true;
            }
            return false;
        }
    };

    @Override
    public void onMethodCallback(int i) {

        if (i == 0){
            appCompatRatingBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Your orders have NOT beed sent", Toast.LENGTH_SHORT).show();
        }else if (i==1){
            appCompatRatingBar.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Your orders have beed sent", Toast.LENGTH_SHORT).show();
        }
    }
}
