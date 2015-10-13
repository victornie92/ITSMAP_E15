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
public class Contacts_info extends Fragment {

    private ListView myListContacts;
    private String[] strListContacts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.contacts,container,false);

        myListContacts = (ListView)v.findViewById(R.id.contacts);

        strListContacts = getResources().getStringArray(R.array.contacts_info);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListContacts);
        myListContacts.setAdapter(adapter);

        return v;

    }
}
