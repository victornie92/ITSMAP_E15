package dk.itsmap.e15.grp4.CalenderActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Vector;

import dk.itsmap.e15.grp4.ArtistActivity;
import dk.itsmap.e15.grp4.InfoActivity.InfoActivity;
import dk.itsmap.e15.grp4.MainActivity;
import dk.itsmap.e15.grp4.MapsActivity;
import dk.itsmap.e15.grp4.R;

public class CalenderActivity extends AppCompatActivity {

    private Context myText;
    CustomPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        myText = this;

        ListView day1 = new ListView(myText);
        ListView day2 = new ListView(myText);
        ListView day3 = new ListView(myText);
        ListView day4 = new ListView(myText);
        ListView day5 = new ListView(myText);

        Vector<View> days = new Vector<View>();

        days.add(day1);
        days.add(day2);
        days.add(day3);
        days.add(day4);
        days.add(day5);

        ViewPager vp = (ViewPager)findViewById(R.id.pager);
        adapter = new CustomPagerAdapter(myText, days);
        vp.setAdapter(adapter);

        Resources res = getResources();
        String[] artist_one = res.getStringArray(R.array.day1_list_artist_array);
        String[] artist_two = res.getStringArray(R.array.day2_list_artist_array);
        String[] artist_three = res.getStringArray(R.array.day3_list_artist_array);
        String[] artist_four = res.getStringArray(R.array.day4_list_artist_array);
        String[] artist_five = res.getStringArray(R.array.day5_list_artist_array);

        day1.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,artist_one));
        day2.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,artist_two));
        day3.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,artist_three));
        day4.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,artist_four));
        day5.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,artist_five));
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
                startActivity(new Intent(CalenderActivity.this, MapsActivity.class));
                return true;
            case R.id.menu_info:
                startActivity(new Intent(CalenderActivity.this, InfoActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

