package shoaziz.example.com.drinkshop.ViewModel;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = Order.class,version = 1)
public abstract class CoreDB extends RoomDatabase {
    private static CoreDB INSTANCE;
    private static final String DB_NAME = "order.db";
    public abstract OrderDao cacheDao();
    public static synchronized CoreDB getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CoreDB.class, "order_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private OrderDao depDao;

        private PopulateDbAsyncTask(CoreDB db) {
            depDao = db.cacheDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
