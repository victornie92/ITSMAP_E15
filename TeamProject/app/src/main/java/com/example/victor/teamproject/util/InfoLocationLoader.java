package com.example.victor.teamproject.util;

import android.app.Activity;
import android.util.Log;

import com.example.victor.teamproject.model.InfoLocation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class InfoLocationLoader {
    Activity activity;

    public InfoLocationLoader(Activity a)
    {
        activity = a;
    }

    public ArrayList<InfoLocation> getInfoLocationList()
    {
        ArrayList<InfoLocation> locations = new ArrayList<InfoLocation>();
        InfoLocation temp;
        InputStream is = activity.getResources().openRawResource(activity.getResources().getIdentifier("raw/infolocations",
                "raw", activity.getPackageName()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",");
                if(items.length==3){
                    try {
                        temp = new InfoLocation(Double.parseDouble(items[1]), Double.parseDouble(items[0]), items[2], "Infomation!");
                        locations.add(temp);
                    } catch (NumberFormatException ex) {
                        Log.e("ERROR", "Bad format of number, ex");
                    } catch (NullPointerException ex) {
                        Log.e("ERROR", "Null value", ex);
                    } catch (Exception ex){
                        Log.e("ERROR", "Something crazy happened", ex);
                    }
                }
            }
        } catch (Exception ex) {
            Log.e("ERROR", "Something wrong during CSV file read", ex);
        }

        return locations;
    }
}