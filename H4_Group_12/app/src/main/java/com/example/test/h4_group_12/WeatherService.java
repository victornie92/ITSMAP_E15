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


public class WeatherService extends Service {

    final Handler weatherHandler = new Handler();
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

        //Create a new timer, with handler to handle every timed event.
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                weatherHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //define city to get data for
                        String city = "aarhus,DK";

                        //Start a Task to retreive the weather info
                        WeatherRetrieveTask task = new WeatherRetrieveTask();
                        task.execute(new String[]{city});
                    }
                });
            }
        };
        //Start the timer with the timerTask(what to do on event), when to start, how often event
        //should happen in ms
        timer.schedule(timerTask, 0, 10000);
    }

    //AsyncTask class to taking care of getting weather info and inform any subscribers of update
    private class WeatherRetrieveTask extends AsyncTask<String, Void, JSONObject>{

        @Override
        protected JSONObject doInBackground(String... params) {
            //Get the HTTP-response from the HTTPhandler-class
            String HTTPresponse = ((new HTTPhandler().getWeatherData(params[0])));

            JSONObject JSONresponse = null;
            //Make a JSONobject out of the result
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
            //Create intent used for notifying subscribers.
            Intent update = new Intent("WeatherUpdate");

            if(jsonObject != null){
                try {
                    //Extract data from JSONobject, put then in the intent and notify.
                    double temp = Math.round(jsonObject.getJSONObject("main").getDouble("temp") - 273.15);
                    double wSpeed = jsonObject.getJSONObject("wind").getDouble("speed");
                    update.putExtra("Temperature",String.valueOf(temp));
                    update.putExtra("WindSpeed",String.valueOf(wSpeed));
                    LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            } else{
                //We somehow failed and should not end up here.
                update.putExtra("Temperature", "JSONresponce");
                update.putExtra("WindSpeed","is Null");
                LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
            }
        }
    }
}
