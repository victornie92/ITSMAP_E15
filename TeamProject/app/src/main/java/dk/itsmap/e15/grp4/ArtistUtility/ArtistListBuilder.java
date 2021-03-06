package dk.itsmap.e15.grp4.ArtistUtility;

import android.app.Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;


/**
 * Build like FragmentsArnieMovies MovieLoader
 */
public class ArtistListBuilder {

    Activity activity;


    public ArtistListBuilder(Activity currentActivity){
        activity = currentActivity;
    }

    public ArrayList<Artist> getArtistList(){
        ArrayList<Artist> artistArrayList = new ArrayList<>();
        Artist tmp;
        
        //Get JSONobject
        JSONObject data;
        try{
            String response = loadJSON();
            data = new JSONObject(response);
            JSONArray artistArray = data.getJSONArray("artists");

            for(int i=0; i < artistArray.length();i++){
                JSONObject artist = artistArray.getJSONObject(i);
                tmp = new Artist(artist.getString("name"),artist.getString("descriptionShort"),
                        artist.getString("descriptionLong"), artist.getString("concertDate"),
                        artist.getString("videoURL"), artist.getString("picUrl"));
                artistArrayList.add(tmp);
            }
            
        } catch (JSONException e){
            e.printStackTrace();
            return null;
        }


        return artistArrayList;
    }

    // http://stackoverflow.com/questions/13814503/reading-a-json-file-in-android
    public String loadJSON(){
        String jsonResponse = null;
        try{
            InputStream inStream = activity.getResources().openRawResource(activity.getResources().
                    getIdentifier("raw/artist", "raw", activity.getPackageName()));
            int size = inStream.available();
            byte[] buf = new byte[size];
            inStream.read(buf);
            inStream.close();
            jsonResponse = new String(buf,"UTF-8");
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonResponse;
    }
}
