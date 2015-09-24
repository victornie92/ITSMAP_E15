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
                        String city = "London,UK";
                        WeatherRetrieveTask task = new WeatherRetrieveTask();
                        task.execute(new String[]{city});
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 10000);
    }

    private class WeatherRetrieveTask extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... params) {
            //String HTTPresponse = ((new HTTPhandler().getWeatherData(params[0])));
            String HTTPresponse = "{\"coord\":{\"lon\":-0.13,\"lat\":51.51},\"weather\":[{\"id\":802,\"main\":\"Clouds\",\"description\":\"scattered clouds\",\"icon\":\"03n\"}],\"base\":\"stations\",\"main\":{\"temp\":285.1,\"pressure\":1018,\"humidity\":81,\"temp_min\":283.15,\"temp_max\":287.15},\"visibility\":10000,\"wind\":{\"speed\":2.1,\"deg\":230},\"clouds\":{\"all\":40},\"dt\":1443127092,\"sys\":{\"type\":1,\"id\":5089,\"message\":0.0122,\"country\":\"GB\",\"sunrise\":1443073833,\"sunset\":1443117191},\"id\":2643743,\"name\":\"London\",\"cod\":200}\n";
//

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
                    double temp = Math.round(jsonObject.getJSONObject("main").getDouble("temp") - 273.15);
                    double wSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                    update.putExtra("Temperature",String.valueOf(temp));
                    update.putExtra("WindSpeed",String.valueOf(wSpeed));
                    LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
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
