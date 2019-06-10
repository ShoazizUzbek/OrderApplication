package shoaziz.example.com.drinkshop.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;


public class OrderViewModel extends AndroidViewModel {
    OrderRepos orderRepos;
    LiveData<List<Order>> orders;

    public OrderViewModel(@NonNull Application application) {
        super(application);
        orderRepos = new OrderRepos(application);
        orders = orderRepos.getAllOrder();
    }
    public void insertAll(Order list){
        orderRepos.insert(list);
    }
    public void delall(){
        orderRepos.deleteAllNotes();
    }
    public LiveData<List<Order>> getAllNotes() {
        return orders;
    }
}
