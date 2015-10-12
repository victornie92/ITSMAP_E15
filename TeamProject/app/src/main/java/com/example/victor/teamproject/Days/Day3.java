package com.example.victor.teamproject.Days;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.victor.teamproject.R;

/**
 * Created by Victor on 09-10-2015.
 */
public class Day3 extends Fragment {

    private ListView myListViewDay3;
    private String[] strListViewDay3;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.day_3,container,false);

        myListViewDay3 = (ListView) v.findViewById(R.id.day3_listView);

        strListViewDay3 = getResources().getStringArray(R.array.day3_list_artist_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListViewDay3);
        myListViewDay3.setAdapter(adapter);

        return v;
    }
}
