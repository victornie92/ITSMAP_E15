package com.example.victor.teamproject.util;

import android.app.Activity;
import android.util.Log;

import com.example.victor.teamproject.model.FoodLocation;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Peter-PC on 08-10-2015.
 */
public class FoodLocationLoader {
    Activity activity;

    public FoodLocationLoader(Activity a)
    {
        activity = a;
    }

    public ArrayList<FoodLocation> getFoodLocationList()
    {
        ArrayList<FoodLocation> locations = new ArrayList<FoodLocation>();
        FoodLocation temp;
        InputStream is = activity.getResources().openRawResource(activity.getResources().getIdentifier("raw/foodlocations",
                "raw", activity.getPackageName()));
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));


        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] items = line.split(",");
                if(items.length==3){
                    try {
                        temp = new FoodLocation(Double.parseDouble(items[1]), Double.parseDouble(items[0]), items[2], "The best food that cures the hangover!");
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