package com.e.navdrawerapp.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BeerApiOkHttp {

    private static final String TAG = "BeerApiOkHttp";
    private static OkHttpClient httpClient;

    public static OkHttpClient getInstance() {
        if (httpClient == null) {
            httpClient = new OkHttpClient();
        }
        return httpClient;
    }

    public static List<Beer> retrieveBeersSync(String url) {
        Log.d(TAG, "retrieveBeersSync from url=" + url);
        List<Beer> result = new ArrayList<>();

        // using GSON library
        Gson gson = new GsonBuilder().create();

        // GET
        Request getRequest = new Request.Builder().url(url).build();

        try {
            // block call, sync request
            Response response = getInstance().newCall(getRequest).execute();

            // if HTTP code is 200
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                JSONArray jsonArray = new JSONArray((responseBody));

                for (int i=0; i< jsonArray.length(); i++) {
                    Beer beer = gson.fromJson(jsonArray.getString(i), Beer.class);
                    Log.d(TAG, "beer name=" + beer.getName());
                    result.add(beer);
                }
                Log.d(TAG, "result size is = " + result.size());

                return result;
            } else {
                // HTTP code 4xx, 5xx
                Log.e(TAG, "cannot execute GET");
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, "error get call, " + e.getLocalizedMessage());
            return null;
        } catch (JSONException e) {
            Log.e(TAG, "error json array, " + e.getLocalizedMessage());
            return null;
        }
    }

    public static String retrieveBeersSyncAsString(String url) {
        Log.d(TAG, "retrieveBeersSync from url=" + url);
        // GET
        Request getRequest = new Request.Builder().url(url).build();

        try {
            // block call, sync request
            Response response = getInstance().newCall(getRequest).execute();

            // if HTTP code is 200
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return responseBody;
            } else {
                // HTTP code 4xx, 5xx
                Log.e(TAG, "cannot execute GET");
                return null;
            }
        } catch (IOException e) {
            Log.e(TAG, "error get call, " + e.getLocalizedMessage());
            return null;
        }
    }

    public static void retrieveBeersAsync(String url, Callback callback) {
        Log.d(TAG, "retrieveBeersAsync from url = " + url);

        Request getRequest = new Request.Builder().url(url).build();

        getInstance().newCall(getRequest).enqueue(callback);
    }
}
