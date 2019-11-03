package com.e.navdrawerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    private SwitchCompat mySwitch;
    private RadioGroup myRadioGroup;
    private ListView myListView;
    private StudentAdapter adapter;
    private Button startServiceBtn;
    private Button startIntentServiceBtn;

    private static final String SWITCH_STATE_KEY = "switch_key";

    private BroadcastReceiver counterReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, "local receiver onReceive");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);


        Intent intent = getIntent();
        if (intent.hasExtra("extra_no")) {
            int no = intent.getIntExtra("extra_no", 0);
            String message = "Intent extra number is= " + no;
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }

        adapter = new StudentAdapter(DrawerAppApplication.getInstance().studentList);

        mySwitch = findViewById(R.id.mySwitch);
        myRadioGroup = findViewById(R.id.radioGroup);
        myListView = findViewById(R.id.myListView);
        startServiceBtn = findViewById(R.id.startServiceBtn);
        startIntentServiceBtn = findViewById(R.id.startIntentServiceBtn);

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, CounterService.class);
                startService(intent);
            }
        });

        startIntentServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, CounterIntentService.class);
                intent.setAction(CounterIntentService.ACTION_COUNT);
                startService(intent);
            }
        });

        final MySharedPref sharedPref = new MySharedPref(this);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SecondActivity.this, "Switch state is ON", Toast.LENGTH_LONG).show();
                }

                sharedPref.addBoolean(SWITCH_STATE_KEY, isChecked);
            }
        });

        myRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                String selectedRadioBtn = "";
                if (checkedId == R.id.radioBtn1) {
                    selectedRadioBtn = "Option 1";
                } else {
                    selectedRadioBtn = "Option 2";
                }
                Toast.makeText(SecondActivity.this, "Selected radio btn is " + selectedRadioBtn, Toast.LENGTH_LONG).show();
            }
        });

        myListView.setAdapter(adapter);

        new CounterAsyncTask().execute(5);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");

        IntentFilter intentFilter = new IntentFilter(CounterIntentService.INTENT_FILTER_COUNT);
        LocalBroadcastManager.getInstance(this).registerReceiver(counterReceiver, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");

        LocalBroadcastManager.getInstance(this).unregisterReceiver(counterReceiver);
        super.onStop();
    }


    private class CounterAsyncTask extends AsyncTask<Integer, Integer, Integer> {

        protected Integer doInBackground(Integer... params) {
            // here is background thread
            Log.d(TAG, "doInBackground");

            int no = params[0];

            int i = 0;
            int sum = 0;
            while (i < no) {
                sum += i;
                i++;
            }

            return sum;
        }

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        protected void onPostExecute(Integer sum) {
            // here is Main Thread
            Log.d(TAG, "onPostExecute, sum is " + sum);
            //sumTextView.text = "Sum is"+ sum
        }
    }
}
