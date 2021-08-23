package com.example.sunmi;

import android.app.AppComponentFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ArrayAdapter platformAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Spinner homePlatformSpinner = findViewById(R.id.switching_platform);

        platformAdapter = ArrayAdapter.createFromResource(this,R.array.platform,android.R.layout.simple_spinner_item);
        platformAdapter.setDropDownViewResource(R.layout.dropdown_stytle);
        homePlatformSpinner.setAdapter(platformAdapter);
        homePlatformSpinner.setOnItemSelectedListener(new ProvOnItemSelectedListener());
    }

    //onItemSelected监听器
    private class ProvOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            TextView textView = (TextView) view;
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(16);
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    }
}
