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
public class Day5 extends Fragment {

    private ListView myListViewDay5;
    private String[] strListViewDay5;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.day_5,container,false);

        myListViewDay5 = (ListView) v.findViewById(R.id.day5_listView);

        strListViewDay5 = getResources().getStringArray(R.array.day5_list_artist_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListViewDay5);
        myListViewDay5.setAdapter(adapter);

        return v;
    }
}
