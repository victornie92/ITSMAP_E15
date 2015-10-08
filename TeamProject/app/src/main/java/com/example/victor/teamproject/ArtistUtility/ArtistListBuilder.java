package com.example.victor.teamproject.ArtistUtility;

import android.app.Activity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;


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

        tmp = new Artist("IU","potato","21-11-2015","https://www.youtube.com/watch?v=ouR4nn1G9r4",
                "http://i.imgur.com/ZCdGAHx.jpg");
        artistArrayList.add(tmp);


        return artistArrayList;
    }
}
