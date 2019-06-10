package shoaziz.example.com.drinkshop.Adapters;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.ViewModel.Order;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder>    {
    List<Order> orders;
    Context context;

    public OrderAdapter(Context context, List<Order> menus) {
        this.context  =context;
        orders = menus;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.order_list_room, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        Order order = orders.get(i);
        viewHolder.text.setText(order.getProductName());
        viewHolder.quan.setText(order.getQuantity());
        viewHolder.price.setText(order.getPrice());
    }



    @Override
    public int getItemCount() {
        return orders.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text, quan, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.name_order);
            quan = itemView.findViewById(R.id.quan_order);
            price = itemView.findViewById(R.id.price_order);
        }


    }
}