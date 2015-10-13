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
 * Created by Victor on 08-10-2015.
 */
public class Day1 extends Fragment {

    private ListView myListViewDay1;
    private String[] strListViewDay1;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.day_1,container,false);

        myListViewDay1 = (ListView) v.findViewById(R.id.day1_listView);

        strListViewDay1 = getResources().getStringArray(R.array.day1_list_artist_array);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, strListViewDay1);
        myListViewDay1.setAdapter(adapter);

        return v;
    }
}
