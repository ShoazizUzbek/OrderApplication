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
import android.widget.Toast;



import java.util.ArrayList;
import java.util.List;

import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.activities.FoodListActivity;
import shoaziz.example.com.drinkshop.data.Menu;


public class MenuAdap extends RecyclerView.Adapter<MenuAdap.ViewHolder>    {
    List<Integer> department_titles = new ArrayList<>();
    Context context;
    public MenuAdap(Context context, List<Integer> menus) {
        this.department_titles  = menus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.menu, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Integer  menu = department_titles.get(i);
        viewHolder.cardView.setImageResource(menu);
        if (i == 0){
            viewHolder.title.setText("Breakfast");
        }else {
            viewHolder.title.setText("Drinks");
        }
//        Log.d("imgurl",menu.getImage());

//        Glide.with(context).load(menu.getImage()).into(viewHolder.cardView);
//        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle gameData = new Bundle();
//                gameData.putString("menu_id",menu.getId());
//                Intent intent = new Intent(context, FoodListActivity.class);
//                intent.putExtras(gameData);
//                context.startActivity(intent);
//            }
//        });
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
            cardView = itemView.findViewById(R.id.menu_name);
            title = itemView.findViewById(R.id.menu_img);
        }


    }
}