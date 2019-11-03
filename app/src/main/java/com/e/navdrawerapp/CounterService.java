package com.e.navdrawerapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class CounterService extends Service {

    private static final String TAG = "CounterService";

    public CounterService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");

        // here is main thread
        //count();

        countInBackgroundThread();

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void count() {
        Log.d(TAG, "count");

        int i = 0;
        while (i < 100) {
            Log.d(TAG, "count=" + i);
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.e(TAG, "thread error, " + e.getLocalizedMessage());
            }
        }
    }

    private void countInBackgroundThread() {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                count();
            }
        };

        new Thread(runnable).start();
    }
}
