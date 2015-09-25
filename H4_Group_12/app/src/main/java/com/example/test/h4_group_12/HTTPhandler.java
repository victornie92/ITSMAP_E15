package com.example.test.h4_group_12;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


//Class to handle HTTP requests
public class HTTPhandler {

    //Base URL part
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public String getWeatherData(String location){
        HttpURLConnection connection = null;
        InputStream inStream = null;

        try{
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
            return buf.toString();
        } catch (Throwable t){
            t.printStackTrace();
        } finally {
            try {
                inStream.close();
            } catch (Throwable t) {
            }
            try{
                connection.disconnect();
            } catch (Throwable t){
            }
        }
        return null;
    }
}
