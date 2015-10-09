package com.example.victor.teamproject;

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
import android.view.Menu;
import android.view.MenuItem;
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

    Button btnUpdate;
    TextView temperatureValue;
    Context context = this;
    WeatherService weatherService;
    boolean serviceBound = false;

   /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUpdate = (Button)findViewById(R.id.btnUpdate);
        temperatureValue = (TextView)findViewById(R.id.tempValue);

        btnUpdate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(serviceBound)
                {
                    if (btnUpdate.getText().toString().equals("Update")) {
                        LocalBroadcastManager.getInstance(context).registerReceiver(
                                weatherUpdateReceiver, new IntentFilter("WeatherUpdate"));
                        btnUpdate.setText("UnUpdate");
                    }
                    else if (btnUpdate.getText().toString().equals("UnUpdate")){
                        LocalBroadcastManager.getInstance(context).unregisterReceiver(
                                weatherUpdateReceiver);
                        btnUpdate.setText("Update");
                    }
                    else{
                        btnUpdate.setText("Program fail");
                    }
                }
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Intent i = new Intent(MainActivity.this, WeatherService.class);
        bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop(){
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
    };*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.menu_home:
                startActivity(new Intent(MainActivity.this, MainActivity.class));
                return true;
            case R.id.menu_artist:
                startActivity(new Intent(MainActivity.this, ArtistActivity.class));
                return true;
            case R.id.menu_map:
                startActivity(new Intent(MainActivity.this, MapsActivity.class));
                return true;
            case R.id.menu_calender:
                startActivity(new Intent(MainActivity.this, CalenderActivity.class));
                return true;
            case R.id.menu_info:
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver weatherUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            temperatureValue.setText(intent.getStringExtra("Temperature"));
        }
    };
}
