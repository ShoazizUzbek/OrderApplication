package shoaziz.example.com.drinkshop.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import shoaziz.example.com.drinkshop.Adapters.OrderAdapter;
import shoaziz.example.com.drinkshop.Interfaces.AdapterCallback;
import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.ViewModel.Order;
import shoaziz.example.com.drinkshop.ViewModel.OrderViewModel;
import shoaziz.example.com.drinkshop.cons.Cons;
import shoaziz.example.com.drinkshop.data.OrderItem;
import shoaziz.example.com.drinkshop.data.PostReq;
import shoaziz.example.com.drinkshop.network.RetroApi;
import shoaziz.example.com.drinkshop.network.RetroInterface;


public class ItemListDialogFragment extends BottomSheetDialogFragment {

    // TODO: Customize parameter argument names
    public ItemListDialogFragment(){}
    OrderViewModel orderViewModel;
    TextView textView, txtTime;
    EditText editText;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    double t = 0,time = 0;
    ProgressBar progressBar;
    Button bt;
    OrderAdapter orderAdapter;
    RelativeLayout rel;
    AdapterCallback adapterCallback;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    List<OrderItem> orderItem;
    public static String last_price;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_item_list_dialog, container, false);
        rel = v.findViewById(R.id.bottom_sheet_layout);
        editText = v.findViewById(R.id.comment_order);
        orderViewModel = ViewModelProviders.of(getActivity()).get(OrderViewModel.class);
        textView = v.findViewById(R.id.total_price);
        recyclerView=  v.findViewById(R.id.recycler_bottom);
        txtTime = v.findViewById(R.id.total_time);
        bt = v.findViewById(R.id.buy_it);
        progressBar = v.findViewById(R.id.prog_item);
            orderItem = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        orderViewModel.getAllNotes().observe(getActivity(), new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> orders) {
                orderAdapter = new OrderAdapter(getContext(),orders);
                recyclerView.setAdapter(orderAdapter);
                t = 0;
                time = 0;
                for (int  o = 0; o < orders.size();o++) {
                    orderItem.add(new OrderItem(orders.get(o).getProductId(),orders.get(o).getQuantity()));
                    if (orders.get(o).getPrice()!=null&&orders.get(o).getQuantity()!=null){
                       // postFoods.add(new PostFood(String.valueOf(orders.get(o).getId()),orders.get(o).getQuantity()));
                        double in = Double.parseDouble(orders.get(o).getPrice());
                        int q = Integer.parseInt(orders.get(o).getQuantity());
                        t = t + in*q;
                        time = time +Double.parseDouble(orders.get(o).getQuantity());
                       // Toast.makeText(getActivity(), String.valueOf(t), Toast.LENGTH_SHORT).show();
                    }
                }
                textView.setText(String.valueOf(t)+" sum");
                txtTime.setText(String.valueOf(time)+ " minutes");
                if (orders.size()>0){
                    last_price = String.valueOf(t);
                }
            }
        });


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //getActivity() is fully created in onActivityCreated and instanceOf differentiate it between different Activities
        if (getActivity() instanceof AdapterCallback)
            adapterCallback = (AdapterCallback) getActivity();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                orderViewModel.delall();
                postOrder();

            }
        });

    }

    private void postOrder() {



        RetroInterface retroInterface = RetroApi.getApiClient().create(RetroInterface.class);
        Call<PostReq> postReqCall = retroInterface.postReq("Token ffe20ab28f326136d4f701d8aa8b48dba483fe4a",new PostReq(editText.getText().toString(),Cons.res,orderItem));
        postReqCall.enqueue(new Callback<PostReq>() {
            @Override
            public void onResponse(Call<PostReq> call, Response<PostReq> response) {
                progressBar.setVisibility(View.INVISIBLE);
                dismiss();
                adapterCallback.onMethodCallback(1);
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "success", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), response+"", Toast.LENGTH_SHORT).show();
                    Log.d("orderlog",response.toString());
                }

            }

            @Override
            public void onFailure(Call<PostReq> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Log.d("orderlogerror",t.toString());
                dismiss();
                adapterCallback.onMethodCallback(0);
            }
        });
    }
}






