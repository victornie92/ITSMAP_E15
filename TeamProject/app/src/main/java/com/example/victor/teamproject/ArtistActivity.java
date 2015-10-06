package com.example.victor.teamproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
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
                startActivity(new Intent(ArtistActivity.this, MapActivity.class));
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
}
