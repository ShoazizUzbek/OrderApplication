package shoaziz.example.com.drinkshop.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shoaziz.example.com.drinkshop.Adapters.BottomAdapter;
import shoaziz.example.com.drinkshop.Adapters.TopAdapter;
import shoaziz.example.com.drinkshop.Interfaces.MyInterface;
import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.ViewModel.Order;
import shoaziz.example.com.drinkshop.ViewModel.OrderViewModel;
import shoaziz.example.com.drinkshop.data.Food;
import shoaziz.example.com.drinkshop.data.Menu;
import shoaziz.example.com.drinkshop.fragments.ItemListDialogFragment;
import shoaziz.example.com.drinkshop.network.RetroApi;
import shoaziz.example.com.drinkshop.network.RetroInterface;

public class FoodListActivity extends AppCompatActivity implements MyInterface {
    String res_id= "1";
    RecyclerView rectop, recBottom;
    TopAdapter menuAdap;
    List<Menu> menus;
    List<Food> foods;
    BottomAdapter bottomAdapter;
    OrderViewModel orderViewModel;
    RelativeLayout relativeLayout;
    BottomNavigationView  bottomNavigationView;
    TextView name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        bottomNavigationView = findViewById(R.id.navigation_list);
        rectop = findViewById(R.id.recycler_top_menu);
        name = findViewById(R.id.name_men);
        recBottom = findViewById(R.id.recycler_bottom_menu);
        rectop.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recBottom.setLayoutManager(new LinearLayoutManager(this));
        relativeLayout = findViewById(R.id.prog_layout);
        relativeLayout.bringToFront();
        orderViewModel = ViewModelProviders.of(FoodListActivity.this).get(OrderViewModel.class);


        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        if (getIntent()!=null){
            res_id = getIntent().getStringExtra("menu_id");
        }
//        if (res_id.equals("")){
//            res_id = "1";
//        }
        Thread  thread = new Thread(){
            @Override
            public void run() {
                super.run();
                json1();
            }
        };
        thread.start();
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                super.run();
                if (res_id==null){
                    json2(4);
                }else {
                    json2(Integer.parseInt(res_id));
                }

            }
        };
        thread2.start();

//        foods = new ArrayList<>();
//        foods.add(new Food("1","1","1","Osh",
//                "Yogli choyxona palov",
//                "http://images.unsplash.com/photo-1510693306332-74189fa090d4?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=670&q=80",
//                "1","1"));
//        bottomAdapter = new BottomAdapter(FoodListActivity.this,foods);
//        recBottom.setAdapter(bottomAdapter);
    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigation_shop:
                    Intent intent = new Intent(FoodListActivity.this, HomeActivity.class);
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

    private void json2(int i) {
        relativeLayout.setVisibility(View.VISIBLE);
        Log.d("idmenu",String.valueOf(i));
        RetroInterface retroInterface = RetroApi.getApiClient().create(RetroInterface.class);
        Call<List<Food>> call = retroInterface.getFood("Token ffe20ab28f326136d4f701d8aa8b48dba483fe4a",String.valueOf(i));
        call.enqueue(new Callback<List<Food>>() {
            @Override
            public void onResponse(Call<List<Food>> call, Response<List<Food>> response) {
                relativeLayout.setVisibility(View.GONE);

                if (response.isSuccessful()&&response.body().size() > 0){
                    foods = response.body();
                    bottomAdapter = new BottomAdapter(FoodListActivity.this,foods);
                    recBottom.setAdapter(bottomAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Food>> call, Throwable t) {
                Toast.makeText(FoodListActivity.this, "Connection failed", Toast.LENGTH_SHORT).show();
                relativeLayout.setVisibility(View.GONE);
            }
        });
    }

    private void json1() {
        RetroInterface retroInterface = RetroApi.getApiClient().create(RetroInterface.class);
        Call<List<Menu>> call = retroInterface.getMenu("Token ffe20ab28f326136d4f701d8aa8b48dba483fe4a","1");
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                Log.d("listjavob",response.toString());
                if (response.isSuccessful()&&response.body().size() > 0){
                    menus = response.body();
                    menuAdap = new TopAdapter(FoodListActivity.this,FoodListActivity.this,menus);
                    rectop.setAdapter(menuAdap);
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Toast.makeText(FoodListActivity.this, "connection failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void selectJson(int i,String n) {
        json2(i);
        if (n!=null){
            name.setText(n);
        }

    }
}
