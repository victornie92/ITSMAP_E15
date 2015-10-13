package dk.itsmap.e15.grp4.Days;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import dk.itsmap.e15.grp4.R;

/**
 * Created by Victor on 09-10-2015.
 */
public class Day4 extends Fragment {

    private ListView myListViewDay4;
    private String[] strListViewDay4;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.day_4,container,false);

        myListViewDay4 = (ListView) v.findViewById(R.id.day4_listView);

        strListViewDay4 = getResources().getStringArray(R.array.day4_list_artist_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListViewDay4);
        myListViewDay4.setAdapter(adapter);

        return v;
    }
}
