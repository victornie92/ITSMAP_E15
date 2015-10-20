package dk.itsmap.e15.grp4.Days;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.zip.Inflater;

import dk.itsmap.e15.grp4.CalenderActivity;
import dk.itsmap.e15.grp4.R;
import dk.itsmap.e15.grp4.ViewPagerAdapter;

/**
 * Created by Victor on 20-10-2015.
 */
public class Days extends Fragment {

    private ListView myListView;
    private String[] myString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

        View v = inflater.inflate(R.layout.days,container,false);

        myListView = (ListView) v.findViewById(R.id.Days);
        myString = getResources().getStringArray(R.array.day1_list_artist_array);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myString);
        myListView.setAdapter(adapter);


        return v;
    }

}
