package com.idrisdev.store.services;

import com.idrisdev.store.models.Product;
import com.idrisdev.store.models.User;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface StoreWebService {
   String URLPATH = "http://idris.tech/api/v1/";
   String LOGIN = "user/login";
   String PRODUCTS = "products";
   String ORDERS = "orders";

   Retrofit retrofit = new Retrofit.Builder()
           .baseUrl(URLPATH)
           .addConverterFactory(GsonConverterFactory.create())
           .build();

   @POST(LOGIN)
    Call<User> user();

   @GET(PRODUCTS)
    Call<Product[]> products();

   @GET(ORDERS)
    Call<Product[]> orders(@Query("id") int id);
}
