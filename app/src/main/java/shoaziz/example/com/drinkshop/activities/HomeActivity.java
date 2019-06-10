package shoaziz.example.com.drinkshop.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import shoaziz.example.com.drinkshop.R;
import shoaziz.example.com.drinkshop.fragments.HomeFragment;
import shoaziz.example.com.drinkshop.fragments.ItemListDialogFragment;
import shoaziz.example.com.drinkshop.fragments.OrderFragment;
import shoaziz.example.com.drinkshop.fragments.ProfileFragment;

public class HomeActivity extends AppCompatActivity
         {
    Toolbar  toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navigation_shop:
                    toolbar.setTitle("Home");
                    fragment = new HomeFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_gifts:
                    ItemListDialogFragment bottomSheetFragment = new ItemListDialogFragment();
                    bottomSheetFragment.show(getSupportFragmentManager(), bottomSheetFragment.getTag());
                    return true;
                case R.id.navigation_cart:
                    toolbar.setTitle("Profile");


                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment) {
             FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
             transaction.replace(R.id.frame_container, fragment);
            // transaction.addToBackStack(null);
             transaction.commit();
    }

             @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



}
