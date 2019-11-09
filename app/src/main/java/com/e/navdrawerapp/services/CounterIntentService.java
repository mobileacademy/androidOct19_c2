package com.e.navdrawerapp.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.List;


public class CounterIntentService extends IntentService {

    private static final String TAG = "CounterIntentService";

    public static final String ACTION_COUNT = "count";
    public static final String ACTION_GET_PACKAGES = "get_packages";

    public static final String INTENT_FILTER_COUNT = "intent_filter_count";

    public CounterIntentService() {
        super("CounterIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent");
        // here is a background thread
        if (intent != null) {

            String intentAction = intent.getAction();
            Log.d(TAG, "intent actions is " + intentAction);

            if (intentAction != null) {
                if (intentAction.equals(ACTION_COUNT)) {
                    count();

                    Intent broadcastIntent = new Intent(INTENT_FILTER_COUNT);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent);
                } else if (intentAction.equals(ACTION_GET_PACKAGES)) {
                    listInstalledPackages();
                }
            }
        }
    }

    private void count() {
        Log.d(TAG, "count");

        int i = 0;
        while (i < 10) {
            Log.d(TAG, "count=" + i);
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Log.e(TAG, "thread error, " + e.getLocalizedMessage());
            }
        }
    }


    private void listInstalledPackages() {
        PackageManager packageManager = getPackageManager();

        List<ApplicationInfo> applicationInfoList = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

        if (applicationInfoList != null) {
            for (ApplicationInfo app: applicationInfoList) {
                Log.d(TAG, "app packageName is " + app.packageName);
            }
        }
    }
}
