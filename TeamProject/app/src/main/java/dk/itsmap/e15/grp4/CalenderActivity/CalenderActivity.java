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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
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
        Vector<View> allDays = new Vector<View>();

        String response = loadJSON();
        JSONArray schedule = null;


        try {

            JSONObject data;
            data = new JSONObject(response);

            schedule = data.getJSONArray("schedule");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (schedule != null){

            for (int i = 0; i < schedule.length(); i++){
                ListView day = new ListView(myText);
                JSONArray artist;
                ArrayList<String> artists = new ArrayList<String>();
                String [] ar = null;
                try {
                    JSONObject x = schedule.getJSONObject(i);
                    artist = x.getJSONArray("artists");

                    for (int j = 0; j < artist.length(); j++){
                        artists.add(artist.getString(j));
                    }

                    ar = new String[artists.size()];
                    ar = artists.toArray(ar);

                    day.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1, ar));
                    allDays.add(day);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            ViewPager vp = (ViewPager)findViewById(R.id.pager);
            adapter = new CustomPagerAdapter(myText, allDays);
            vp.setAdapter(adapter);

        }


    }

    public String loadJSON(){
        String jsonResponse = null;
        try{
            InputStream inStream = getResources().openRawResource(getResources().
                    getIdentifier("raw/artist", "raw", getPackageName()));
            int size = inStream.available();
            byte[] buf = new byte[size];
            inStream.read(buf);
            inStream.close();
            jsonResponse = new String(buf,"UTF-8");
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }
        return jsonResponse;
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

