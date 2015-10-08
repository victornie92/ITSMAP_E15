package com.example.victor.teamproject;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.IBinder;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Victor on 05-10-2015.
 */
public class WeatherService extends Service {

    String city = "aarhus,DK";
    final Handler weatherHandler = new Handler();
    private final IBinder binder = new LocalWeatherBinder();

    public class LocalWeatherBinder extends Binder{
        WeatherService getService () {return WeatherService.this;}
    }

    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        /*Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                weatherHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        WeatherRetrieveTask task = new WeatherRetrieveTask();
                        task.execute(new String[]{city});
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 10000);*/
    }

    private class WeatherRetrieveTask extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... params) {

            String HTTPresponse = ((new HTTPhandler().getWeatherData(params[0])));

            JSONObject JSONresponse = null;

            try{
                JSONresponse = new JSONObject(HTTPresponse);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return JSONresponse;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject){
            super.onPostExecute(jsonObject);

            Intent update = new Intent("WeatherUpdate");

            if(jsonObject != null)
            {
                try{
                    double temp = Math.round(jsonObject.getJSONObject("main").getDouble("temp") - 273.15);
                    update.putExtra("Temperature",String.valueOf(temp));
                    LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
            else {
                update.putExtra("Temperature", "JSONresponse");
                LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
            }
        }
    }
}
