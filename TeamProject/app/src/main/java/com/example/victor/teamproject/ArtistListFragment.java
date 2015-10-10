package com.example.victor.teamproject;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.victor.teamproject.ArtistUtility.Artist;
import com.example.victor.teamproject.ArtistUtility.ArtistAdapter;

import java.util.ArrayList;

/**
 * Build like FragmentsArnieMovies MovieAdapter
 */
public class ArtistListFragment extends Fragment {

    private ListView artistListView;
    private ArtistAdapter artistAdapter;
    private ArrayList<Artist> artists;

    private ArtistInterface selectorInterface;

    public ArtistListFragment(){
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.artistlist,container,false);

        artistListView = (ListView) view.findViewById(R.id.listView);
        updateMovies();

        return view;
    }

    //Doesn't work without this function
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try{
            selectorInterface = (ArtistInterface) activity;
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    public void onArtistSelected(int position){
        if(selectorInterface != null)
            selectorInterface.onArtistSelected(position);
    }

    public void setArtists(ArrayList<Artist> artistslist){
        artists = (ArrayList<Artist>) artistslist.clone();
    }

    private void updateMovies() {
        if(selectorInterface != null)
            artists = selectorInterface.getArtistList();
        if(artists!=null){
            artistAdapter = new ArtistAdapter(getActivity(),artists);
            artistListView.setAdapter(artistAdapter);

            artistListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    onArtistSelected(position);
                }
            });
        }
    }
}
