package com.e.navdrawerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private SwitchCompat mySwitch;
    private RadioGroup myRadioGroup;
    private ListView myListView;
    private StudentAdapter adapter;

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

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(SecondActivity.this, "Switch state is ON", Toast.LENGTH_LONG).show();
                }
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

    }
}
