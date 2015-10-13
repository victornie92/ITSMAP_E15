package com.example.victor.teamproject.Info;

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
 * Created by Victor on 12-10-2015.
 */
public class buyTicket_info extends Fragment {

    private ListView myListTicket;
    private String[] strListTicket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.buyticket,container,false);

        myListTicket = (ListView)v.findViewById(R.id.buyTicket);

        strListTicket = getResources().getStringArray(R.array.butTicket_information);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListTicket);
        myListTicket.setAdapter(adapter);

        return v;

    }
}
