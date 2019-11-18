package com.e.navdrawerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.e.navdrawerapp.database.BeerEntity;
import com.e.navdrawerapp.database.BeerViewModel;
import com.e.navdrawerapp.database.ViewModelFactory;
import com.e.navdrawerapp.network.Beer;
import com.e.navdrawerapp.network.BeerApiOkHttp;
import com.e.navdrawerapp.services.CounterIntentService;
import com.e.navdrawerapp.services.GetBeersIntentService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeersActivity extends AppCompatActivity {

    private ListView beersListView;
    private BeersAdapter beersAdapter;

    private List<Beer> beerList = new ArrayList<>();
    private static final String TAG = "BeersActivity";

    private BroadcastReceiver localReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "message received!");

            // retrive list
            String response = intent.getStringExtra("result");

            if (response == null) {
                Toast.makeText(BeersActivity.this, "Try again later!", Toast.LENGTH_LONG).show();
            } else {
                // using GSON library
                Gson gson = new GsonBuilder().create();
                JSONArray jsonArray = null;

                List<Beer> result = new ArrayList<>();

                try {
                    jsonArray = new JSONArray((response));
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Beer beer = gson.fromJson(jsonArray.getString(i), Beer.class);
                        Log.d(TAG, "beer name=" + beer.getName());
                        result.add(beer);
                    }
                    Log.d(TAG, "result size is = " + result.size());

                    beersAdapter.updateList(result);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beers);
        Log.d(TAG, "onCreate");

        beersListView = findViewById(R.id.beerListView);

        beersAdapter = new BeersAdapter(beerList);
        beersListView.setAdapter(beersAdapter);

        BeerViewModel viewModel =
                ViewModelProviders.of(this, new ViewModelFactory(getApplication())).get(BeerViewModel.class);


        viewModel.getBeerList().observe(this, new Observer<List<BeerEntity>>() {
            @Override
            public void onChanged(List<BeerEntity> beerEntities) {
                Log.d(TAG, "**** on Change ****");

                // here is Ui Thread
                if (beerEntities != null) {
                    List<Beer> result = new ArrayList<>();

                    Log.d(TAG, "new list size is " + beerEntities.size());

                    for (int i=0; i< beerEntities.size();i++) {
                        BeerEntity entity = beerEntities.get(i);
                        Beer beer = new Beer(entity.id, entity.name, entity.desc, entity.imgUrl);
                        result.add(beer);
                    }

                    beersAdapter.updateList(result);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume -> start intent service to GET beer list");

        // FOR GET LIST SYNC (example 1)
        startService(new Intent(this, GetBeersIntentService.class));

        // GET ASYNC (example 2)
//        BeerApiOkHttp.retrieveBeersAsync("https://api.punkapi.com/v2/beers", new Callback() {
//            @Override
//            public void onFailure(@NotNull Call call, @NotNull IOException e) {
//                Log.e(TAG, "onFailure");
//            }
//
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                // HTTP CODE 200
//                if (response.isSuccessful()) {
//                    Log.d(TAG, "send broadcast message");
//                    Intent broadcastIntent = new Intent("GET_CALL_DONE_REFRESH_UI");
//                    broadcastIntent.putExtra("result", response.body().string());
//                    LocalBroadcastManager.getInstance(BeersActivity.this).sendBroadcast(broadcastIntent);
//
//                } else {
//                    // HTTP code 4xx 5xx
//                    Toast.makeText(BeersActivity.this, "Try again later!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        // register this activity to receive broadcasts with this message GET_CALL_DONE_REFRESH_UI
        IntentFilter intentFilter = new IntentFilter("GET_CALL_DONE_REFRESH_UI");
        LocalBroadcastManager.getInstance(this).registerReceiver(localReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();

        LocalBroadcastManager.getInstance(this).unregisterReceiver(localReceiver);
    }
}
