package com.example.victor.teamproject;

import com.example.victor.teamproject.ArtistUtility.Artist;

import java.util.ArrayList;

/**
 * Build like FragmentsArnieMovies
 */
public interface ArtistInterface {

    public void onArtistSelected(int position);
    public ArrayList<Artist> getArtistList();
    public Artist getCurrentArtist();
}
