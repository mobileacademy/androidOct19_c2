package com.e.navdrawerapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.e.navdrawerapp.BeersAdapter;
import com.e.navdrawerapp.network.Beer;
import com.e.navdrawerapp.network.BeerApiOkHttp;

import java.util.List;

public class GetBeersIntentService extends IntentService {

    private static final String TAG = "GetBeersIntentService";

    public GetBeersIntentService() {
        super("GetBeersIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");

        // here is background thread
        //List<Beer> result = BeerApiOkHttp.retrieveBeersSync("https://api.punkapi.com/v2/beers");

        String result = BeerApiOkHttp.retrieveBeersSyncAsString("https://api.punkapi.com/v2/beers");

        if (result == null) {
            // inform user that call was not executed
        } else {
            // TODO send broadcast with message call DONE
            Log.d(TAG, "send broadcast message");
            Intent broadcastIntent = new Intent("GET_CALL_DONE_REFRESH_UI");
            broadcastIntent.putExtra("result", result);
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
        }
    }
}
