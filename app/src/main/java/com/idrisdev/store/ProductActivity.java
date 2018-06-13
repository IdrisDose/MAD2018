package com.idrisdev.store;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.User;

public class ProductActivity extends AppCompatActivity {
    private Product mProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Handles what happens to any arguments passed into this activity via it's Intent (assigned by previous activities)
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            //If arguments bundle is not empty assign this classes mProduct to the parceable
            this.mProduct = arguments.getParcelable("product");
        } else {
            //Else blank product
            this.mProduct = new Product(0, "null", "null");
        }

        TextView productName = findViewById(R.id.product_activity_name);
        TextView productPrice = findViewById(R.id.product_activity_price);
        TextView productDescription = findViewById(R.id.product_activity_description);

        productName.setText(getString(R.string.product_name_string, mProduct.getName()));
        productPrice.setText(mProduct.getDisplayPrice());
        productDescription.setText(mProduct.getDescription());

        FloatingActionButton addToCartBtn = findViewById(R.id.add_to_cart_btn);

        //Fires when the add to cart button is pressed.
        addToCartBtn.setOnClickListener(button -> {
            User.getInstance().getCart().addToCart(this.mProduct);
            Snackbar.make(button, "Added to cart", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }

}
