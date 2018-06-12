package com.idrisdev.store;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.idrisdev.store.fragments.AccountFragment;
import com.idrisdev.store.fragments.HomeFragment;
import com.idrisdev.store.fragments.ProductsFragment;
import com.idrisdev.store.fragments.TicketsFragment;
import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private User mUser;

    private HomeFragment mHomeFragment = new HomeFragment();
    private ProductsFragment mProductsFragment = new ProductsFragment();
    private TicketsFragment mTicketsFragment = new TicketsFragment();
    private AccountFragment mAccountFragment = new AccountFragment();
    private AlertDialog mLogoutDialog;
    private ArrayList<Product> mProducts;
    private Bundle mBundle;

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
        if (extras != null) {
            this.mUser = extras.getParcelable("user");
        }else{
            this.mUser = new User(0,"null","null");
        }


        //Gets products from the server
        // TODO: add actual server capabilities
        new GetProductsTask(MainActivity.this).execute(5);


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
                            openHomeFragment();
                            return true;
                        case R.id.navigation_products:
                            openProductFragment();
                            return true;
                        case R.id.navigation_tickets:
                            openTicketFragment();
                            return true;
                        case R.id.navigation_account:
                            openAccountFragment();
                            return true;
                    }
                    return false;
                }
            };



    private void openHomeFragment(){
        mBundle = new Bundle();
        mBundle.putParcelable("user",this.mUser);

        mHomeFragment.setArguments(mBundle);
        setFragment(mHomeFragment);
    }

    private void openProductFragment() {
        mBundle = new Bundle();
        mBundle.putParcelable("user",this.mUser);
        mBundle.putParcelableArrayList("products",mProducts);

        mProductsFragment.setArguments(mBundle);
        setFragment(mProductsFragment);
    }
    private void openTicketFragment(){
        mBundle = new Bundle();
        mBundle.putParcelable("user",this.mUser);

        mTicketsFragment.setArguments(mBundle);
        setFragment(mTicketsFragment);
    }

    private void openAccountFragment(){
        mBundle = new Bundle();
        mBundle.putParcelable("user",this.mUser);

        mTicketsFragment.setArguments(mBundle);
        setFragment(mTicketsFragment);
    }


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
                showLogoutDialog();
                return true;
            case R.id.menu_action_cart:
                showCartActivity();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showCartActivity() {
        Intent settingsScreen = new Intent(this,SettingsActivity.class);
        settingsScreen.putExtra("user",this.mUser);
        startActivity(settingsScreen);
    }

    public void openSettings() {
        //TODO: Polish this.
        Intent settingsScreen = new Intent(this,SettingsActivity.class);
        settingsScreen.putExtra("user",this.mUser);
        startActivity(settingsScreen);
    }


    public void handleLogout(){
        mLogoutDialog.dismiss();
        //Makes a new Intent to swap to
        Intent loginScreen = new Intent(this,LandingActivity.class);
        //Stops any other activities running regarding this app.
        loginScreen.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        //Starts the loginScreen intent (using the LandingActivity layout/Activity)
        startActivity(loginScreen);
        //Calls the finish method on this activity.
        this.finish();
    }

    /**
     * Shows a confirmation dialog to make sure the user wants to logout.
     */
    public void showLogoutDialog(){
        mLogoutDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("Logout")
                .setMessage("Would you like to logout?")
                //If Yes is Tapped, call the handleDialog method
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    handleLogout();
                })
                //If tapped no, dismiss the dialog
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
    }

    /**
     * Take care of popping the fragment back stack or finishing the activity
     * as appropriate.
     */
    @Override
    public void onBackPressed() {
        showLogoutDialog();
    }

    private void addProduct(int id, String name, String description){
        this.mProducts.add(new Product(id,name,description));
    }

    private class GetProductsTask extends AsyncTask<Integer,Void,ArrayList<Product>>{
        private MainActivity mActivity;
        GetProductsTask(MainActivity activity){
            this.mActivity = activity;
        }
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param count The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected ArrayList<Product> doInBackground(Integer... count) {
            ArrayList<Product> products = new ArrayList<>();
            //TODO: Replace this with fetching actual products.
            for(int index = 0; index <= 5; index++){
                products.add(new Product(index,"Product "+ index,"Some Random Description"));
            }
            return products;
        }

        /**
         * Runs on the UI thread before {@link #doInBackground}.
         *
         * @see #onPostExecute
         * @see #doInBackground
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param products The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(ArrayList<Product> products) {
            super.onPostExecute(products);
            mActivity.mProducts = products;
            mActivity.mUser.addOrder(products.get(1));
            openHomeFragment();
        }
    }
}
