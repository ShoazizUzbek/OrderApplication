package shoaziz.example.com.drinkshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shoaziz.example.com.drinkshop.Interfaces.MyInterface;
import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.activities.FoodListActivity;
import shoaziz.example.com.drinkshop.data.Food;
import shoaziz.example.com.drinkshop.data.Menu;


public class TopAdapter extends RecyclerView.Adapter<TopAdapter.ViewHolder>    {
    List<Menu> department_titles = new ArrayList<>();
    Context context;
    MyInterface myInterface;
    public TopAdapter(MyInterface myInterface,Context context, List<Menu> menus) {
        this.myInterface = myInterface;
        this.department_titles  = menus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu_list, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Menu  menu = department_titles.get(i);
        Log.d("imgurl",menu.getImage());
        viewHolder.title.setText(menu.getName());
        Glide.with(context).load(menu.getImage()).into(viewHolder.cardView);
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myInterface.selectJson(Integer.parseInt(menu.getId()),menu.getName());
//                Bundle gameData = new Bundle();
//                gameData.putString("menu_id",menu.getRestaurantId());
//                Intent intent = new Intent(context, FoodListActivity.class);
//                intent.putExtras(gameData);
//                context.startActivity(intent);
            }
        });
    }



    @Override
    public int getItemCount() {
        return department_titles.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        ImageView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.menu_img_top_list);
            title = itemView.findViewById(R.id.name_menu_top_list);
        }

    }
}