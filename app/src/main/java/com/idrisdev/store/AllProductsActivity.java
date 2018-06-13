package com.idrisdev.store;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.idrisdev.store.adapters.AllProductAdapter;
import com.idrisdev.store.models.ProductList;
import com.idrisdev.store.models.User;

public class AllProductsActivity extends AppCompatActivity {

    private static final String TAG = "StoreApp";
    private static User sUser;
    private ProductList mProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_products);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Get the user object which should be parsed into the Activity from either registration or login form (LandingActivity)
        sUser = User.getInstance();
        Bundle args = getIntent().getExtras();
        if (args != null) {
            this.mProducts = args.getParcelable("products");
        }


        RecyclerView allProductsRv = findViewById(R.id.all_products_activity_rv);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        allProductsRv.setLayoutManager(layoutManager);
        allProductsRv.setItemAnimator(new DefaultItemAnimator());
        allProductsRv.setAdapter(new AllProductAdapter(getApplicationContext(), this.mProducts));

        TextView allProductsActivityTitle = findViewById(R.id.all_products_activity_title);
        allProductsActivityTitle.setText(getString(R.string.products_title_with_count, this.mProducts.getSize()));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: AllProductActivity");
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
        getMenuInflater().inflate(R.menu.topnav_menu, menu);
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
        switch (item.getItemId()) {
            case R.id.menu_action_logout:
                showLogoutDialog();
                return true;
            case R.id.menu_action_cart:
                showCartActivity();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Opens the Cart Activity
     */
    private void showCartActivity() {
        Intent cartScreen = new Intent(this, CartActivity.class);
        startActivity(cartScreen);
    }

    /**
     * Handles what happens when you click the logout option on the topnav menu
     */
    public void handleLogout() {
        //Makes a new Intent to swap to
        Intent loginScreen = new Intent(this, LandingActivity.class);
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
    public void showLogoutDialog() {
        new AlertDialog.Builder(AllProductsActivity.this)
                .setTitle("Logout")
                .setMessage("Would you like to logout? (this will also empty your cart)")
                //If Yes is Tapped, call the handleDialog method
                .setPositiveButton("Yes", (dialog, which) -> {
                    dialog.dismiss();
                    handleLogout();
                })
                //If tapped no, dismiss the dialog
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss()).show();
    }
}
