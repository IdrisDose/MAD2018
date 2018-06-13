package com.idrisdev.store.utils;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Snippet from linda (http://git.io/v13pg)
 */
public class HttpHelper {
    private static final String URLPATH = "http://idris.tech/api/v1/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    /**
     * Returns text from a URL on a web server
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String downloadUrlGet(String path) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(URLPATH +path).build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }else {
            throw new IOException("Exception: response code: " + response.code());
        }
    }

    /**
     * Returns text from a URL on a web server
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String downloadUrlPost(String path, String jsonParams) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, jsonParams);
        Request request = new Request.Builder()
                .url(URLPATH +path)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }else {
            throw new IOException("Exception: response code: " + response.code());
        }

    }

    /**
     * Returns text from a URL on a web server
     *
     * @param auth
     * @return
     * @throws IOException
     */
    public static String loginURL(String auth) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, auth);
        Request request = new Request.Builder()
                .url(URLPATH +"user/login")
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if(response.isSuccessful()){
            return response.body().string();
        }else {
            throw new IOException("Exception: response code: " + response.code());
        }

    }

}

