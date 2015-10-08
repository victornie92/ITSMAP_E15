package com.example.victor.teamproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.victor.teamproject.ArtistUtility.Artist;
import com.example.victor.teamproject.ArtistUtility.ArtistListBuilder;

import java.util.ArrayList;


public class ArtistActivity extends AppCompatActivity implements ArtistInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        ArtistListBuilder potato = new ArtistListBuilder(this);
        potato.getArtistList();
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
            case R.id.menu_artist:
                startActivity(new Intent(ArtistActivity.this, ArtistActivity.class));
                return true;
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
    public ArrayList<Artist> getArtistList() {
        return null;
    }
}
