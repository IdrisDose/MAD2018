package com.idrisdev.store;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.User;

public class ProductActivity extends AppCompatActivity {
    private User mUser;
    private Product mProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mUser = extras.getParcelable("user");
            this.mProduct = extras.getParcelable("product");
        }else{
            this.mUser = new User(0,"null","null");
            this.mProduct = new Product(0, "null","null");
        }

        TextView productName = findViewById(R.id.product_activity_name);
        TextView productPrice = findViewById(R.id.product_activity_price);
        TextView productDescription = findViewById(R.id.product_activity_description);

        productName.setText(getString(R.string.product_name_string,mProduct.getName()));
        productPrice.setText(mProduct.getDisplayPrice());
        productDescription.setText(mProduct.getDescription());

        FloatingActionButton addToCartBtn = findViewById(R.id.add_to_cart_btn);
//        addToCartBtn.setOnClickListener(new AddToCartButtonClick(this));
        addToCartBtn.setOnClickListener(new AddToCartButtonClick(this));
    }

    public void addToCart(View button) {
        this.mUser.getCart().addToCart(this.mProduct);
        Snackbar.make(button, "Added to cart", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


    class AddToCartButtonClick implements View.OnClickListener{

        private ProductActivity mProductActivity;
        AddToCartButtonClick(ProductActivity productActivity){
            this.mProductActivity = productActivity;
        }
        /**
         * Called when a view has been clicked.
         *
         * @param button The view that was clicked.
         */
        @Override
        public void onClick(View button) {
            this.mProductActivity.addToCart(button);
        }
    }


}
