package shoaziz.example.com.drinkshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatRatingBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import shoaziz.example.com.drinkshop.Interfaces.AdapterCallback;
import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.activities.DetailActivity;
import shoaziz.example.com.drinkshop.data.Food;
import shoaziz.example.com.drinkshop.data.Menu;


public class BottomAdapter extends RecyclerView.Adapter<BottomAdapter.ViewHolder>    {
    List<Food> department_titles = new ArrayList<>();
    Context context;
    AdapterCallback adapterCallback;
    public BottomAdapter(Context context, List<Food> menus) {
        this.department_titles  = menus;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_food, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        final Food food = department_titles.get(i);
        final String id = food.getId();
        final String name = food.getName();
        final String des = food.getDescription();
        final String time = food.getCookingTime();
        final String price = food.getPrice();
        final String img = food.getImage();

        //cal = food.getcalory and rate;
        if (food.getRating().getRate()!=null){
            viewHolder.appCompatRatingBar.setRating(Float.parseFloat(food.getRating().getRate()));
        }else {
            viewHolder.appCompatRatingBar.setRating(4.5f);
        }

        viewHolder.title.setText(food.getName());
        Log.d("imggg",food.getDescription());
        Glide.with(context).load(food.getImage()).into(viewHolder.cardView);
        viewHolder.price.setText(food.getPrice()+" sum");
        viewHolder.time.setText(food.getCookingTime()+" min");
        viewHolder.half.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",name);
                intent.putExtra("des",des);
                intent.putExtra("time",time);
                intent.putExtra("price",price);
                intent.putExtra("img",img);
                intent.putExtra("alldata",food);
                context.startActivity(intent);

            }
        });
        viewHolder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  adapterCallback.onMethodCallback(food);
                viewHolder.uchirma.setVisibility(View.VISIBLE);
                Animation animation = AnimationUtils.loadAnimation(context.getApplicationContext(),R.anim.moving);
                viewHolder.uchirma.startAnimation(animation);
            }
        });
    }



    @Override
    public int getItemCount() {
        return department_titles.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, price,time;
        ImageView cardView,select, uchirma;
        LinearLayout half;
        AppCompatRatingBar appCompatRatingBar;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            appCompatRatingBar = itemView.findViewById(R.id.rate_list);
            half = itemView.findViewById(R.id.half_lay);
            cardView = itemView.findViewById(R.id.img_list);
            title = itemView.findViewById(R.id.list_name);
            price = itemView.findViewById(R.id.pricetxt);
            time = itemView.findViewById(R.id.timetxt);
            select = itemView.findViewById(R.id.select_it);
            uchirma = itemView.findViewById(R.id.uchadigan);
        }


    }
}