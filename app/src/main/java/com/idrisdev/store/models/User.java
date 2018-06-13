package com.idrisdev.store.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User{
    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("email")
    private String mEmail;

    @SerializedName("active")
    private boolean mActive;

    //ProductList Orders used to store OwnProducts
    private ProductList mOrders;

    //Cart used to store this sessions cart
    private Cart mCart;

    //Used for the singleton
    private static User instance;

    /**
     * Returns a static instance of the User Class
     * @return User
     */
    public static User getInstance(){
        if(instance == null){
            instance = new User();
        }
        return instance;
    }

    /**
     * Empty Constructor for Singleton
     */
    private User(){
        //Empty for singleton
        mCart = new Cart();
        mOrders = new ProductList();
        mActive = true;
    }

    /**
     * Gets the User's ID
     * @return String - This User's ID
     */
    public int getId() {
        return mId;
    }

    /**
     * Gets the User's Name
     * @return String - This User's Name
     */
    public String getName() {
        return mName;
    }

    /**
     * Gets this User's Email
     * @return String - This User's Email
     */
    public String getEmail() {
        return mEmail;
    }

    /**
     * Used to check if the User is Active
     * @return boolen This User's active status
     */
    public boolean isActive() {
        return mActive;
    }

    /**
     * Sets the User's ID
     * @param id int
     */
    public void setId(int id) {
        this.mId = id;
    }

    /**
     * Sets the User's Name
     * @param name String
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Sets the User's Email
     * @param email String
     */
    public void setEmail(String email) {
        this.mEmail = email;
    }

    /**
     * Sets the User's Active Status
     * @param active boolean
     */
    public void setActive(boolean active) {
        this.mActive = active;
    }

    /**
     * Sets the User's orders
     * @param products List<Product>
     */
    public void setOrders(ArrayList<Product> products){
        this.mOrders = new ProductList(products);
    }

    /**
     * Add a product to the User's orders
     * @param product Product
     */
    public void addOrder(Product product){
        this.mOrders.addProduct(product);
    }

    /**
     * Gets the user's owned products
     * @return mOrders OwnedProducts
     */
    public ProductList getOrders() {
        return mOrders;
    }

    /**
     * Gets the user's Cart
     * @return Cart
     */
    public Cart getCart(){
        return this.mCart;
    }

    /**
     * Sets the user's cart
     * @param cart Cart
     */
    public void setCart(Cart cart) {
        this.mCart = cart;
    }

    /**
     * Gets the size of this users order list
     * @return the size of mOrders.
     */
    public int getOrderCount(){
        return this.mOrders.getSize();
    }

    /**
     * Returns a pretty/formatted version of IsActive used for display
     * @return String
     */
    public String getActivePretty(){
        return this.mActive ? "Active" : "Deactive";
    }

    /**
     * Used to statically place the productlist cart items into orders productlist
     */
    public void purchaseCart(){
        ProductList myCart = this.mCart.getCartItems();
        this.mOrders.getAllProducts().addAll(myCart.getAllProducts());
    }
}
