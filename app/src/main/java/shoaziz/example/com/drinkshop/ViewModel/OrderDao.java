package shoaziz.example.com.drinkshop.ViewModel;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface OrderDao  {
    @Query("SELECT * FROM ordertb ORDER BY ProductName ASC")
    LiveData<List<Order>> getOrders();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOrder(Order order);

    @Delete
    void deleteOrder(Order order);

    @Query("DELETE FROM ordertb")
    void deleteAll();

}
