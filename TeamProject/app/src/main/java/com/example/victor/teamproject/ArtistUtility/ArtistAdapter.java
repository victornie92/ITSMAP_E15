package com.example.victor.teamproject.ArtistUtility;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Anders on 08-10-2015.
 */
public class ArtistAdapter extends BaseAdapter {

    Context context;
    ArrayList<Artist> artists;
    Artist artist = null;

    @Override
    public int getCount() {
        if(artists!=null)
            return artists.size();
        else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        if(artists!=null)
            return artists.get(position);
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
