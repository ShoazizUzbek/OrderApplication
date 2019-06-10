package shoaziz.example.com.drinkshop.ViewModel;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


public class OrderRepos {
    OrderDao orderDao;
    LiveData<List<Order>> allOrder;

    public OrderRepos(Application application){
        CoreDB coreDB = CoreDB.getInstance(application);
        orderDao = coreDB.cacheDao();
        allOrder = orderDao.getOrders();
    }
    public LiveData<List<Order>> getAllOrder(){
        return allOrder;
    }
    public void insert(Order departmentCache){
        new InsertNoteAsyncTask(orderDao).execute(departmentCache);
    }
    public void deleteAllNotes() {
        new DeleteAllNotesAsyncTask(orderDao).execute();
    }
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDao noteDao;

        private DeleteAllNotesAsyncTask(OrderDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }
    private static class InsertNoteAsyncTask extends AsyncTask<Order,Void,Void>{
        OrderDao orderDao;
        public InsertNoteAsyncTask(OrderDao  orderDao) {
            this.orderDao = orderDao;
        }

        @Override
        protected Void doInBackground(Order... orders) {
            orderDao.insertOrder(orders[0]);
            return null;
        }
    }

}
