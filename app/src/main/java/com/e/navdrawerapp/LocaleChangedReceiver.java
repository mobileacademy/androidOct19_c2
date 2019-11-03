package com.e.navdrawerapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class LocaleChangedReceiver extends BroadcastReceiver {

    private static final String TAG = "LocaleChangedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        // This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d(TAG, "onReceive");

        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                Log.d(TAG, "action is " + action);

            }
        }

    }
}
