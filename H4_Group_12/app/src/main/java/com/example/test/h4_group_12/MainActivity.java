package com.example.test.h4_group_12;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    Button subButton;
    TextView temperatureValue,windSpeedValue;
    WeatherService weatherService;
    boolean serviceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subButton = (Button)findViewById(R.id.subBtn);
        temperatureValue = (TextView)findViewById(R.id.tempValue);
        windSpeedValue = (TextView)findViewById(R.id.windSpeed);

        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(serviceBound){
                    if(subButton.getText().toString().equals("Subscribe")) {
                        LocalBroadcastManager.getInstance(context).registerReceiver(
                                weatherUpdateReceiver, new IntentFilter("WeatherUpdate"));
                        subButton.setText("Unsubscribe");
                    }
                    else if(subButton.getText().toString().equals("Unsubscribe")) {
                        LocalBroadcastManager.getInstance(context).unregisterReceiver(
                                weatherUpdateReceiver);
                        subButton.setText("Subscribe");
                    }
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, WeatherService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(serviceBound){
            unbindService(serviceConnection);
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            WeatherService.LocalWeatherBinder binder = (WeatherService.LocalWeatherBinder) service;
            weatherService = binder.getService();
            serviceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };

    private BroadcastReceiver weatherUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            temperatureValue.setText(intent.getStringExtra("Temperature"));
            windSpeedValue.setText(intent.getStringExtra("WindSpeed"));
        }
    };
}
