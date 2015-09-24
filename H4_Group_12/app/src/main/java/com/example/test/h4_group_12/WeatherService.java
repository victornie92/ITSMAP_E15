package com.example.test.h4_group_12;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

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
                        WeatherRetrieveTask task = new WeatherRetrieveTask();
                        task.execute("London,UK");
                        /*
                        Intent update = new Intent("WeatherUpdate");
                        update.putExtra("Temperature", String.valueOf(i++));
                        update.putExtra("WindSpeed",String.valueOf(i++));
                        LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);*/
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 10000);
    }

    private class WeatherRetrieveTask extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... params) {
            String HTTPresponse = ((new HTTPhandler().getWeatherData(params[0])));

            JSONObject JSONresponse = null;
            try {
                JSONresponse = new JSONObject(HTTPresponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return JSONresponse;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            super.onPostExecute(jsonObject);
            Intent update = new Intent("WeatherUpdate");

            if(jsonObject != null){
                try {
                    double temp = jsonObject.getJSONObject("main").getDouble("temp");
                    double wSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                    update.putExtra("Temperature",String.valueOf(temp));
                    update.putExtra("WindSpeed",String.valueOf(wSpeed));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            } else{
                update.putExtra("Temperature", "JSONresponce");
                update.putExtra("WindSpeed","is Null");
                LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
            }
        }
    }
}
