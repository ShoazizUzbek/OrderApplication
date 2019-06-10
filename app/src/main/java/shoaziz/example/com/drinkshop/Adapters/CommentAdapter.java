package shoaziz.example.com.drinkshop.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
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


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import shoaziz.example.com.drinkshop.Interfaces.AdapterCallback;
import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.activities.DetailActivity;
import shoaziz.example.com.drinkshop.data.Comments;
import shoaziz.example.com.drinkshop.data.Food;


public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>    {
    List<Comments> comments = new ArrayList<>();
    Context context;
    AdapterCallback adapterCallback;
    public CommentAdapter(Context context, List<Comments> comments) {
        this.comments  = comments;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_comment, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Comments comment = comments.get(i);
        viewHolder.user.setText(comment.getCustomer().getId());
        viewHolder.com.setText(comment.getText());
        List<String> colors;

        colors=new ArrayList<String>();

        colors.add("#5E97F6");
        colors.add("#9CCC65");
        colors.add("#FF8A65");
        colors.add("#9E9E9E");
        colors.add("#9FA8DA");
        colors.add("#90A4AE");
        colors.add("#AED581");
        colors.add("#F6BF26");
        colors.add("#FFA726");
        colors.add("#4DD0E1");
        colors.add("#BA68C8");
        colors.add("#A1887F");

// all colors used by gmail application :) may be,

        // genrating random num from 0 to 11 because you can add more or less

        Random r = new Random();
        int i1 = r.nextInt(11- 0) + 0;

//genrating shape with colors

        GradientDrawable draw = new GradientDrawable();
        draw.setShape(GradientDrawable.OVAL);
        draw.setColor(Color.parseColor(colors.get(i1)));

// assigning to textview
        viewHolder.contact_name_circle.setBackground(draw);
    }



    @Override
    public int getItemCount() {
        return comments.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user, com,contact_name_circle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.user_name);
            com = itemView.findViewById(R.id.comment);
            contact_name_circle = itemView.findViewById(R.id.oval);
        }


    }
}