package dk.itsmap.e15.grp4;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import dk.itsmap.e15.grp4.Days.Days;



public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    String Titles[];
    int NumbOfDays;
    public int id = 0;

    
    public ViewPagerAdapter(FragmentManager fm,String mTitles[], int mNumbOfDays) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfDays = mNumbOfDays;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
              class Days extends Fragment {

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

            Days day1 = new Days();
            return day1;
        }
        else if(position == 1){
            class Days extends Fragment {

                private ListView myListView;
                private String[] myString;


                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

                    View v = inflater.inflate(R.layout.days,container,false);

                    myListView = (ListView) v.findViewById(R.id.Days);
                    myString = getResources().getStringArray(R.array.day2_list_artist_array);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myString);
                    myListView.setAdapter(adapter);


                    return v;
                }
            }
            Days day2 = new Days();
            return day2;
        }
        else if (position == 2){
            class Days extends Fragment {

                private ListView myListView;
                private String[] myString;


                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

                    View v = inflater.inflate(R.layout.days,container,false);

                    myListView = (ListView) v.findViewById(R.id.Days);
                    myString = getResources().getStringArray(R.array.day3_list_artist_array);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myString);
                    myListView.setAdapter(adapter);


                    return v;
                }
            }
            Days day3 = new Days();
            return day3;
        }
        else if(position == 3){
            class Days extends Fragment {

                private ListView myListView;
                private String[] myString;


                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

                    View v = inflater.inflate(R.layout.days,container,false);

                    myListView = (ListView) v.findViewById(R.id.Days);
                    myString = getResources().getStringArray(R.array.day4_list_artist_array);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myString);
                    myListView.setAdapter(adapter);


                    return v;
                }
            }
            Days day4 = new Days();
            return day4;
        }
        else {
            class Days extends Fragment {

                private ListView myListView;
                private String[] myString;


                @Override
                public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){

                    View v = inflater.inflate(R.layout.days,container,false);

                    myListView = (ListView) v.findViewById(R.id.Days);
                    myString = getResources().getStringArray(R.array.day5_list_artist_array);


                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1, myString);
                    myListView.setAdapter(adapter);


                    return v;
                }
            }
            Days day5 = new Days();
            return day5;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip
    @Override
    public String getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfDays;
    }
}
