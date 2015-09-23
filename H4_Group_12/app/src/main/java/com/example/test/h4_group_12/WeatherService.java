package com.example.test.h4_group_12;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

/**
 * Created by Teardrop Ducky on 23/09/2015.
 */
public class WeatherService extends Service {

    private final IBinder binder = new LocalWeatherBinder();

    public class LocalWeatherBinder extends Binder {
        WeatherService getService(){
            return WeatherService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(WeatherService.this, "fap fap fap", Toast.LENGTH_SHORT).show();
    }

    public void message(){
        Toast.makeText(WeatherService.this, "shit works", Toast.LENGTH_SHORT).show();
    }
}
