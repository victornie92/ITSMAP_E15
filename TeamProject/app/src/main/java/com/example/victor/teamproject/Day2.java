package com.example.victor.teamproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Victor on 08-10-2015.
 */
public class Day2 extends Fragment {

    private ListView myListViewDay2;
    private String[] strListViewDay2;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.day_2,container,false);

        myListViewDay2 = (ListView) v.findViewById(R.id.day2_listView);

        strListViewDay2 = getResources().getStringArray(R.array.day2_list_artist_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListViewDay2);
        myListViewDay2.setAdapter(adapter);

        return v;
    }
}
