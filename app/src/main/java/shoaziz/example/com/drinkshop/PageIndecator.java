package shoaziz.example.com.drinkshop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PageIndecator extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;
    public PageIndecator(Context context) {

        this.context = context;

    }

    public int[] slide_imgs = {R.drawable.img1,R.drawable.img2,R.drawable.img3};
    public String[] slide_des = {"Eat healthy","Be healthy","Live Healthy"};
    @Override
    public int getCount() {
        return slide_des.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == (ConstraintLayout)o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView imageView = view.findViewById(R.id.imageView);
        TextView textView = view.findViewById(R.id.textView);
        imageView.setImageResource(slide_imgs[position]);
        textView.setText(slide_des[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
