package com.idrisdev.store;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.idrisdev.store.fragments.CartFragment;
import com.idrisdev.store.fragments.HomeFragment;
import com.idrisdev.store.fragments.ProductsFragment;
import com.idrisdev.store.fragments.TicketsFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setup Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Setup Bottom Navigation
        BottomNavigationView bottomNavigation = findViewById(R.id.bottom_navigation);

        //Handle Bottom Navigation Item Clicks
        bottomNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            /**
             * Called when an item in the bottom navigation menu is selected.
             *
             * @param item The selected item
             * @return true to display the item as the selected item and false if the item should not
             * be selected. Consider setting non-selectable items as disabled preemptively to
             * make them appear non-interactive.
             */
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.navigation_home:
                        setFragment(new HomeFragment());
                        return true;
                    case R.id.navigation_products:
                        setFragment(new ProductsFragment());
                        return true;
                    case R.id.navigation_tickets:
                        setFragment(new TicketsFragment());
                        return true;
                    case R.id.navigation_cart:
                        setFragment(new CartFragment());
                        return true;
                }
                return false;
            }
        });

        //Set Default Fragment to open up to:
        setFragment(new HomeFragment());
    }

    /**
     * Sets ups a fragment transaction;
     * @param fragment Fragment to start transaction
     */
    private void setFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();
    }
}
