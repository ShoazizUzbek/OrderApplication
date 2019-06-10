package shoaziz.example.com.drinkshop.fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shoaziz.example.com.drinkshop.Adapters.MenuAdap;
import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.cons.Cons;
import shoaziz.example.com.drinkshop.data.Menu;
import shoaziz.example.com.drinkshop.network.RetroApi;
import shoaziz.example.com.drinkshop.network.RetroInterface;

/**
 * A simple {@link Fragment} subclass.
 */
public class BuildingA3 extends Fragment {

    List<Menu> menus;
    MenuAdap menuAdap;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ImageView imageView;
    Button button;
    public BuildingA3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Cons.res = "2";

        View view =  inflater.inflate(R.layout.fragment_building_a3, container, false);
        button = view.findViewById(R.id.reloada3);

        //imageView = view.findViewById(R.id.img_iuta4);
        recyclerView = view.findViewById(R.id.recycler_in_frag1);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        progressBar = view.findViewById(R.id.progress_a3);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        json();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                json();
            }
        });
    }
    void json(){
        RetroInterface retroInterface = RetroApi.getApiClient().create(RetroInterface.class);
        Call<List<Menu>> call = retroInterface.getMenu("Token ffe20ab28f326136d4f701d8aa8b48dba483fe4a","2");
        call.enqueue(new Callback<List<Menu>>() {
            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                button.setVisibility(View.GONE);
                if (response.isSuccessful()&&response.body().size() > 0){
                    menus = response.body();
                    menuAdap = new MenuAdap(getContext(),menus);
                    recyclerView.setAdapter(menuAdap);
                    progressBar.setVisibility(View.INVISIBLE);

                }else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Log.d("javobi",response.message());
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                button.setVisibility(View.VISIBLE);
                Toast.makeText(getActivity(), "Connection failed", Toast.LENGTH_SHORT).show();
                //  Log.d("errr",t.getMessage());
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

    }
}
