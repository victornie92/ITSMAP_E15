package dk.itsmap.e15.grp4.InfoActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;
import java.util.Vector;

import dk.itsmap.e15.grp4.ArtistActivity;
import dk.itsmap.e15.grp4.CalenderActivity.CalenderActivity;
import dk.itsmap.e15.grp4.CalenderActivity.CustomPagerAdapter;
import dk.itsmap.e15.grp4.MainActivity;
import dk.itsmap.e15.grp4.MapsActivity;
import dk.itsmap.e15.grp4.R;

/**
 * Created by Victor on 12-10-2015.
 */
public class InfoActivity extends AppCompatActivity {

    private Context myText;
    CustomPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        myText = this;

        ListView buyTicket = new ListView(myText);
        ListView developer = new ListView(myText);
        ListView info = new ListView(myText);

        Vector<View> information = new Vector<View>();

        information.add(buyTicket);
        information.add(developer);
        information.add(info);

        ViewPager vp = (ViewPager)findViewById(R.id.pager);
        adapter = new CustomPagerAdapter(myText, information);
        vp.setAdapter(adapter);

        buyTicket.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,new String[]{"Buy Ticket"}));
        developer.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,new String[]{"Victor Nielsen", "Peter Tien Mai", "Anders Revsgaard Andreasen"}));
        info.setAdapter(new ArrayAdapter<String>(myText, android.R.layout.simple_list_item_1,new String[]{"SmartPhone APP, IHA, Findlandsgade 22, 8200, Aarhus N, Danmark"}));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_home:
                startActivity(new Intent(InfoActivity.this, MainActivity.class));
                return true;
            case R.id.menu_artist:
                startActivity(new Intent(InfoActivity.this, ArtistActivity.class));
                return true;
            case R.id.menu_map:
                startActivity(new Intent(InfoActivity.this, MapsActivity.class));
                return true;
            case R.id.menu_calender:
                startActivity(new Intent(InfoActivity.this, CalenderActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
