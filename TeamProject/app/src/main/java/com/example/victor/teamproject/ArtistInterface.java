package com.example.victor.teamproject;

import com.example.victor.teamproject.ArtistUtility.Artist;

import java.util.ArrayList;

/**
 * Created by Anders on 08-10-2015.
 */
public interface ArtistInterface {

    public void onArtistSelected(int position);
    public ArrayList<Artist> getArtistList();
    public Artist getCurrentArtist();
    public void viewSpecial();
}
