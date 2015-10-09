package com.example.victor.teamproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[];
    int NumbOfDays;

    
    public ViewPagerAdapter(FragmentManager fm,CharSequence mTitles[], int mNumbOfDays) {
        super(fm);

        this.Titles = mTitles;
        this.NumbOfDays = mNumbOfDays;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {

        if(position == 0) // if the position is 0 we are returning the First tab
        {
            Day1 day1 = new Day1();
            return day1;
        }
        else if (position == 1)// As we are having 2 tabs if the position is now 0 it must be 1 so we are returning second tab
        {
            Day2 day2 = new Day2();
            return day2;
        }
        else if(position == 2)
        {
            Day3 day3 = new Day3();
            return day3;
        }
        else if(position == 3)
        {
            Day4 day4 = new Day4();
            return day4;
        }
        else
        {
            Day5 day5 = new Day5();
            return day5;
        }

    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfDays;
    }
}
