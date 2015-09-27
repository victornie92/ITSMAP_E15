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
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Context context = this;
    Button subButton;
    TextView temperatureValue,windSpeedValue, windDirection, city, updateTime;
    Spinner Cities;
    WeatherService weatherService;
    boolean serviceBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subButton = (Button)findViewById(R.id.subBtn);
        temperatureValue = (TextView)findViewById(R.id.tempValue);
        windSpeedValue = (TextView)findViewById(R.id.windSpeed);
        windDirection = (TextView)findViewById(R.id.WindDirection);
        city = (TextView)findViewById(R.id.City);
        updateTime = (TextView)findViewById(R.id.lastUpdate);

        Cities = (Spinner)findViewById(R.id.CitiesPicker);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Cities_array,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Cities.setAdapter(adapter);
        Cities.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Message Service a new city is picked
                String pickedCity = parent.getItemAtPosition(position).toString();
                Intent update = new Intent("CityUpdate");
                update.putExtra("City", pickedCity);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcast(update);
                Log.i("MainActivity", "Send message to service that new city is picked");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Not sure what we use this for :D
            }
        });
        Log.i("MainActivity", "Made Spinner and created view");


        subButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (serviceBound) {
                    //Two cases
                    // 1 - we aren't subscribed, but want to subscribe
                    if (subButton.getText().toString().equals("Subscribe")) {
                        LocalBroadcastManager.getInstance(context).registerReceiver(
                                weatherUpdateReceiver, new IntentFilter("WeatherUpdate"));
                        subButton.setText("Unsubscribe");
                        Log.i("MainActivity", "Pressed to subscribe so started listing for updates");
                    }
                    // 2 - we are subscribed, but we want to unsubscribe
                    else if (subButton.getText().toString().equals("Unsubscribe")) {
                        LocalBroadcastManager.getInstance(context).unregisterReceiver(
                                weatherUpdateReceiver);
                        subButton.setText("Subscribe");
                        Log.i("MainActivity", "Pressed unsubscribe and no longer listen for updates");
                    }
                } else {
                    subButton.setText("The programmers messed up");
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, WeatherService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
        Log.i("MainActivity", "Activity started and bond with service");
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(serviceBound){
            unbindService(serviceConnection);
        }
        Log.i("MainActivity","Activity stopped");
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

    //BroadcastReceiver to update view, when subscribed and service makes and update.
    private BroadcastReceiver weatherUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            temperatureValue.setText(intent.getStringExtra("Temperature"));
            windSpeedValue.setText(intent.getStringExtra("WindSpeed"));
            windDirection.setText(intent.getStringExtra("WindDirection"));
            city.setText(intent.getStringExtra("City"));
            updateTime.setText("Last update: "+ getCurrentTime());
            Log.i("MainActivity", "Update received and view updated");
        }
    };

    private String getCurrentTime(){
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        return String.valueOf(hour)+":"+String.valueOf(minute)+":"+String.valueOf(second);
    }
}
