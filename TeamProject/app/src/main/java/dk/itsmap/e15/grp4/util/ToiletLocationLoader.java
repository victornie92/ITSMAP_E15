package dk.itsmap.e15.grp4.util;

import android.app.Activity;
import android.util.Log;

import dk.itsmap.e15.grp4.model.ToiletLocation;

import java.io.InputStream;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;


/**
 * Created by Peter-PC on 08-10-2015.
 */
public class ToiletLocationLoader {

    Activity activity;

    public ToiletLocationLoader(Activity a)
        {
            activity = a;
        }

        public ArrayList<ToiletLocation> getToiletLocationList()
        {
            ArrayList<ToiletLocation> locations = new ArrayList<ToiletLocation>();
            ToiletLocation temp;
            InputStream is = activity.getResources().openRawResource(activity.getResources().getIdentifier("raw/toiletlocations",
                    "raw", activity.getPackageName()));
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));


            try {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] items = line.split(",");
                    if(items.length==3){
                        try {
                            temp = new ToiletLocation(Double.parseDouble(items[1]), Double.parseDouble(items[0]), items[2], "Toilets!");
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