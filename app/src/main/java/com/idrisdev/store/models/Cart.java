package com.idrisdev.store.models;

import java.util.ArrayList;

/**
 * Created by Idris on 6/12/2018.
 */
public class Cart {
    private ArrayList<Product> mItems;
    private double mCartPrice;

    public Cart(){
        //Need Empty for initialization
    }

    public Cart(ArrayList<Product> items, double cartPrice){
        this.mItems = items;
        this.mCartPrice = cartPrice;
    }

    public void addToCart(Product product){
        this.mItems.add(product);
        this.mCartPrice = this.mCartPrice+product.getPrice();
    }

    public ArrayList<Product> getCartItems(){
        return this.mItems;
    }

    public double getCartPrice(){
        return this.mCartPrice;
    }
}
