package dk.itsmap.e15.grp4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import dk.itsmap.e15.grp4.R;

/**
 * Created by Victor on 12-10-2015.
 */
public class InfoActivity extends AppCompatActivity {

    ViewPager pager;
    ViewInfoAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence info[] = {"Buy Ticket", "Contact"};
    int numbOfinfo = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        adapter = new ViewInfoAdapter(getSupportFragmentManager(), info, numbOfinfo);

        pager = (ViewPager)findViewById(R.id.pager);
        pager.setAdapter(adapter);

        tabs = (SlidingTabLayout)findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true);

        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer()
        {
            @Override
            public int getIndicatorColor(int position){
                return getResources().getColor(R.color.tabsScrollColor);
            }

        });
        tabs.setViewPager(pager);
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
