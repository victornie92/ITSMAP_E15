package dk.itsmap.e15.grp4;

import dk.itsmap.e15.grp4.ArtistUtility.Artist;

import java.util.ArrayList;

/**
 * Build like FragmentsArnieMovies
 */
public interface ArtistInterface {

    public void onArtistSelected(int position);
    public ArrayList<Artist> getArtistList();
    public Artist getCurrentArtist();
}
