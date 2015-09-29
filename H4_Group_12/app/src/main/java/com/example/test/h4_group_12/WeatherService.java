package com.example.test.h4_group_12;

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


public class WeatherService extends Service {

    String city = "aarhus,DK";
    Context context = this;
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

        LocalBroadcastManager.getInstance(context).registerReceiver(
                CityUpdateReceiver, new IntentFilter("CityUpdate"));
        //Create a new timer, with handler to handle every timed event.
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                weatherHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Log.i("WeatherService", "Update tick happened");
                        //Start a Task to retreive the weather info
                        WeatherRetrieveTask task = new WeatherRetrieveTask();
                        task.execute(new String[]{city});
                    }
                });
            }
        };
        //Start the timer with the timerTask(what to do on event), when to start, how often event
        //should happen in ms
        timer.schedule(timerTask, 0, 600000); //Was 10000 in video
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
                Log.i("WeatherService", "HTTP response converted to JSON");
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
                    double wDirection = jsonObject.getJSONObject("wind").getDouble("deg");
                    update.putExtra("Temperature",String.valueOf(temp));
                    update.putExtra("WindSpeed",String.valueOf(wSpeed));
                    update.putExtra("WindDirection",findWindDirection(wDirection));
                    update.putExtra("City", jsonObject.getString("name"));
                    Log.i("WeatherService", "Update packed and send to MainActivity via Broadcast");
                    LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            } else{
                //We somehow failed and should not end up here.
                update.putExtra("Temperature", "JSONresponce");
                update.putExtra("WindSpeed","is Null");
                update.putExtra("WindDirection",findWindDirection(420));
                update.putExtra("City","Null");
                LocalBroadcastManager.getInstance(WeatherService.this).sendBroadcast(update);
            }
        }

        //Convert deg to a string
        private String findWindDirection(double wDirection){
            String direction = "Moon";
            if(wDirection < 11.25)
                return direction = "North";
            if(wDirection < 33.75)
                return direction = "North-NorthEast";
            if(wDirection < 56.25)
                return direction = "NorthEast";
            if(wDirection < 78.75)
                return  direction = "East-NorthEast";
            if(wDirection < 101.25)
                return  direction = "East";
            if(wDirection < 123.75)
                return  direction = "East-SouthEast";
            if(wDirection < 146.25)
                return  direction = "SouthEast";
            if(wDirection < 168.75)
                return  direction = "South-SouthEast";
            if(wDirection < 191.25)
                return  direction = "South";
            if(wDirection < 213.75)
                return  direction = "South-SouthWest";
            if(wDirection < 236.25)
                return  direction = "SouthWest";
            if(wDirection < 258.75)
                return  direction = "West-SouthWest";
            if(wDirection < 281.25)
                return  direction = "West";
            if(wDirection < 303.75)
                return  direction = "West-NorthWest";
            if(wDirection < 326.25)
                return  direction = "NorthWest";
            if(wDirection < 348.75)
                return  direction = "North-NorthWest";
            if(wDirection <= 360)
                return  direction = "North";
            else
                return direction = "WTF :D";
        }
    }

    private BroadcastReceiver CityUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            city = intent.getStringExtra("City");
            Log.i("WeatherService", "City update received from MainActivity via Broadcast");
            //Should probably start a task too, so we update as soon as the user subscribe
            WeatherRetrieveTask task = new WeatherRetrieveTask();
            task.execute(new String[]{city});
        }
    };
}
