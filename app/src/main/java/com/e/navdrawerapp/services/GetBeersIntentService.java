package com.e.navdrawerapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.e.navdrawerapp.BeersAdapter;
import com.e.navdrawerapp.database.AppDatabase;
import com.e.navdrawerapp.database.BeerEntity;
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
        // example 1
        List<Beer> result = BeerApiOkHttp.retrieveBeersSync("https://api.punkapi.com/v2/beers");

        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());

        if (result != null && result.size() > 0) {
            for (int i=0; i< result.size(); i++) {
                Beer beer = result.get(i);

                BeerEntity beerEntity = new BeerEntity(beer.getId(), beer.getName(),
                        beer.getDescription(), beer.getImageUrl());
                Log.d(TAG, "insert into db");
                db.beerDao().insertAll(beerEntity);
            }
            Log.d(TAG, "db update!!!");
        }

        // GET ASYNC (example 2)
//        String result = BeerApiOkHttp.retrieveBeersSyncAsString("https://api.punkapi.com/v2/beers");
//
//        if (result == null) {
//            // inform user that call was not executed
//        } else {
//            // TODO send broadcast with message call DONE
//            Log.d(TAG, "send broadcast message");
//            Intent broadcastIntent = new Intent("GET_CALL_DONE_REFRESH_UI");
//            broadcastIntent.putExtra("result", result);
//            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
//        }
    }
}
