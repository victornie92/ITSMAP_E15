package com.example.victor.teamproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class ArtistActivity extends AppCompatActivity {

    Button btnArtist, btnMap, btnCalender, btnInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        btnArtist = (Button)findViewById(R.id.menu_artist);
        btnMap = (Button)findViewById(R.id.menu_map);
        btnCalender = (Button)findViewById(R.id.menu_calender);
        btnInfo = (Button)findViewById(R.id.menu_info);

        Toast.makeText(ArtistActivity.this, "Potato", Toast.LENGTH_SHORT).show();
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_menu) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btnHome(MenuItem item) {
        Intent i = new Intent(ArtistActivity.this, MainActivity.class);

        startActivityForResult(i,2);
        finish();
    }


    public void btnArtist(MenuItem item) {
        Intent i = new Intent(ArtistActivity.this, ArtistActivity.class);

        startActivityForResult(i,2);
        finish();
    }

    public void btnMap (MenuItem item) {
        Intent i = new Intent(ArtistActivity.this, MapActivity.class);

        startActivityForResult(i, 2);
        finish();
    }

    public void btnCalender (MenuItem item) {
        Intent i = new Intent(ArtistActivity.this, CalenderActivity.class);

        startActivityForResult(i,2);
        finish();
    }

    public void btnInfo (MenuItem item) {
        Intent i = new Intent(ArtistActivity.this, InfoActivity.class);

        startActivityForResult(i,2);
        finish();
    }
}
