package com.example.victor.teamproject;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Victor on 05-10-2015.
 */
public class HTTPhandler {

    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public String getWeatherData(String location) {
        HttpURLConnection connection = null;
        InputStream inStream = null;

        try {
            //Connect to URL
            connection = (HttpURLConnection) (new URL(BASE_URL + location)).openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            //Get response
            StringBuffer buf = new StringBuffer();
            inStream = connection.getInputStream();
            BufferedReader bufRead = new BufferedReader(new InputStreamReader(inStream));
            String line = null;
            while ((line = bufRead.readLine()) != null)
                buf.append(line + "\r\n");

            //Close and return response
            inStream.close();
            connection.disconnect();
            Log.i("HTTPhandler", "HTTP received");
            return buf.toString();
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (Throwable t) {
            }
            try {
                connection.disconnect();
            } catch (Throwable t) {
            }
        }
        return null;
    }
}
