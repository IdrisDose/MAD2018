package com.idrisdev.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.idrisdev.store.fragments.CartFragment;
import com.idrisdev.store.fragments.HomeFragment;
import com.idrisdev.store.fragments.ProductsFragment;
import com.idrisdev.store.fragments.TicketsFragment;
import com.idrisdev.store.models.User;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "StoreApp";

    private User mUser;
    private HomeFragment mHomeFragment = new HomeFragment();
    private ProductsFragment mProductsFragment = new ProductsFragment();
    private TicketsFragment mTicketsFragment = new TicketsFragment();
    private CartFragment mCartFragment = new CartFragment();

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
        bottomNavigation.setOnNavigationItemSelectedListener(onItemSelected);



        //Get the user object which should be parsed into the Activity from either registration or login form (LandingActivity)
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            mUser = getIntent().getParcelableExtra("user");
            if(mUser == null){
                Log.e("MAD", "Error retrieving user");
            }else{
                Bundle bundle = new Bundle();
                bundle.putParcelable("user",mUser);
                mHomeFragment.setArguments(bundle);
            }

        }

        //Set Default Fragment to open up to:
        setFragment(mHomeFragment);

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

    private BottomNavigationView.OnNavigationItemSelectedListener onItemSelected =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
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
                            setFragment(mHomeFragment);
                            return true;
                        case R.id.navigation_products:
                            setFragment(mProductsFragment);
                            return true;
                        case R.id.navigation_tickets:
                            setFragment(mTicketsFragment);
                            return true;
                        case R.id.navigation_cart:
                            setFragment(mCartFragment);
                            return true;
                    }
                    return false;
                }
            };

    /**
     * Initialize the contents of the Activity's standard options menu.  You
     * should place your menu items in to <var>menu</var>.
     * <p>
     * <p>This is only called once, the first time the options menu is
     * displayed.  To update the menu every time it is displayed, see
     * {@link #onPrepareOptionsMenu}.
     * <p>
     * <p>The default implementation populates the menu with standard system
     * menu items.  These are placed in the {@link Menu#CATEGORY_SYSTEM} group so that
     * they will be correctly ordered with application-defined menu items.
     * Deriving classes should always call through to the base implementation.
     * <p>
     * <p>You can safely hold on to <var>menu</var> (and any items created
     * from it), making modifications to it as desired, until the next
     * time onCreateOptionsMenu() is called.
     * <p>
     * <p>When you add items to the menu, you can implement the Activity's
     * {@link #onOptionsItemSelected} method to handle them there.
     *
     * @param menu The options menu in which you place your items.
     * @return You must return true for the menu to be displayed;
     * if you return false it will not be shown.
     * @see #onPrepareOptionsMenu
     * @see #onOptionsItemSelected
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topnav_menu,menu);
        return true;
    }

    /**
     * This hook is called whenever an item in your options menu is selected.
     * The default implementation simply returns false to have the normal
     * processing happen (calling the item's Runnable or sending a message to
     * its Handler as appropriate).  You can use this method for any items
     * for which you would like to do processing without those other
     * facilities.
     * <p>
     * <p>Derived classes should call through to the base class for it to
     * perform the default menu handling.</p>
     *
     * @param item The menu item that was selected.
     * @return boolean Return false to allow normal menu processing to
     * proceed, true to consume it here.
     * @see #onCreateOptionsMenu
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.menu_action_settings:
                openSettings();
                return true;
            case R.id.menu_action_logout:
                handleLogout();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Handles what happens when a user clicks on the Logout Button/Menu Option.
     */
    private void handleLogout() {
        //Makes a new Intent to swap to
        Intent loginScreen = new Intent(this,LandingActivity.class);

        //Stops any other activies running regarding this app.
        loginScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        //Starts the loginScreen intent (using the LandingActivity layout/Activity)
        startActivity(loginScreen);

        //Calls the finish method on this activity.
        this.finish();
    }

    private void openSettings() {
        //TODO: Handle Settings Option
        Log.e(TAG, "Not available yet");
    }
}
