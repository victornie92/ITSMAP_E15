package com.example.victor.teamproject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.Map;

public class CalenderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        String [] theDays = {"Day1:", "Day2:", "Day3:", "Day4:", "Day5:"};
        ListAdapter adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, theDays);
        ListView listView = (ListView) findViewById(R.id.Days);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l){

                String daysPicked = "You selected " + String.valueOf(adapterView.getItemAtPosition(position));

                Toast.makeText(CalenderActivity.this, daysPicked, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calender, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity(new Intent(CalenderActivity.this, MainActivity.class));
                return true;
            case R.id.menu_artist:
                startActivity(new Intent(CalenderActivity.this, ArtistActivity.class));
                return true;
            case R.id.menu_map:
                startActivity(new Intent(CalenderActivity.this, MapActivity.class));
                return true;
            case R.id.menu_calender:
                startActivity(new Intent(CalenderActivity.this, CalenderActivity.class));
                return true;
            case R.id.menu_info:
                startActivity(new Intent(CalenderActivity.this, InfoActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

