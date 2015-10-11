package com.example.victor.teamproject;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.example.victor.teamproject.ArtistUtility.Artist;
import com.example.victor.teamproject.ArtistUtility.ArtistListBuilder;

import java.util.ArrayList;

/**
 * Build like FragmentsArnieMovies (simpler)
 */
public class ArtistActivity extends AppCompatActivity implements ArtistInterface {

    private ArrayList<Artist> artists;
    private ArtistListFragment listFragment;
    private ArtistFragment artistFragment;

    private LinearLayout artistListContainer;
    private LinearLayout artistContainer;

    int currentArtist = 0;
    boolean hasSeletectedArtist = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.multiplane_artist);

        artistListContainer = (LinearLayout)findViewById(R.id.list_container);
        artistContainer = (LinearLayout)findViewById(R.id.details_container);

        artists = new ArtistListBuilder(this).getArtistList();

        listFragment = new ArtistListFragment();
        listFragment.setArtists(artists);

        artistFragment = new ArtistFragment();
        artistFragment.setArtist(artists.get(currentArtist));


        getSupportFragmentManager().beginTransaction().
                add(R.id.list_container, listFragment, "artist_list").
                add(R.id.details_container, artistFragment, "artist").commit();
        artistListContainer.setVisibility(View.VISIBLE);
        artistContainer.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed() {

        if(hasSeletectedArtist){
            artistListContainer.setVisibility(View.VISIBLE);
            artistContainer.setVisibility(View.GONE);
            hasSeletectedArtist = false;
        } else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_artist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.menu_home:
                startActivity(new Intent(ArtistActivity.this, MainActivity.class));
                return true;
<<<<<<< HEAD
=======
            case R.id.menu_artist:
                //startActivity(new Intent(ArtistActivity.this, ArtistActivity.class));
                return true;
>>>>>>> origin/master
            case R.id.menu_map:
                startActivity(new Intent(ArtistActivity.this, MapsActivity.class));
                return true;
            case R.id.menu_calender:
                startActivity(new Intent(ArtistActivity.this, CalenderActivity.class));
                return true;
            case R.id.menu_info:
                startActivity(new Intent(ArtistActivity.this, InfoActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onArtistSelected(int position) {
        if(artistFragment!=null) {
            Artist selectedArtist = artists.get(position);
            if(selectedArtist!=null){
                currentArtist = position;
                artistFragment.setArtist(selectedArtist);
            }
        }
        artistListContainer.setVisibility(View.GONE);
        artistContainer.setVisibility(View.VISIBLE);
        hasSeletectedArtist = true;
    }

    @Override
    public ArrayList<Artist> getArtistList() {
        return artists;
    }

    @Override
    public Artist getCurrentArtist() {
        if(artists!=null)
            return artists.get(currentArtist);
        else
            return null;
    }

}
