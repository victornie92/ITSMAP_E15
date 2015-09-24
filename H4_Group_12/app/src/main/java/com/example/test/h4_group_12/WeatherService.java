package com.example.test.h4_group_12;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.LogRecord;

/**
 * Created by Teardrop Ducky on 23/09/2015.
 */
public class WeatherService extends Service {

    final Handler weatherHandler = new Handler();
    private final IBinder binder = new LocalWeatherBinder();
    int i = 0;

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

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                weatherHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Intent update = new Intent("WeatherUpdate");
                        update.putExtra("Temperature", String.valueOf(i++));
                        update.putExtra("WindSpeed",String.valueOf(i++));
                        LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
                    }
                });
            }
        };
        timer.schedule(timerTask,0,10000);
    }
}
