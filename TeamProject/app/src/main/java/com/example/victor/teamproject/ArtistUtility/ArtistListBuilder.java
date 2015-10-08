package com.example.victor.teamproject.ArtistUtility;

import android.app.Activity;

import java.util.ArrayList;


/**
 * Build like FragmentsArnieMovies MovieLoader
 */
public class ArtistListBuilder {

    Activity activity;


    public ArtistListBuilder(Activity currentActivity){
        activity = currentActivity;
    }

    public ArrayList<Artist> artistList = new ArrayList<>();
}
