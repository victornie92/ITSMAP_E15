package com.example.victor.teamproject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.victor.teamproject.Info.Contacts_info;
import com.example.victor.teamproject.Info.buyTicket_info;

/**
 * Created by Victor on 12-10-2015.
 */
public class ViewInfoAdapter extends FragmentStatePagerAdapter {

    CharSequence Info[];
    int NumbOfInfo;

    public ViewInfoAdapter(FragmentManager fm,CharSequence mInfo[], int mNumbOfInfo)
    {
        super(fm);
        this.Info = mInfo;
        this.NumbOfInfo = mNumbOfInfo;
    }


    @Override
    public Fragment getItem(int position) {
        if(position == 0)
        {
            buyTicket_info buyTicketInfo = new buyTicket_info();
            return buyTicketInfo;
        }
        else {
            Contacts_info contactsInfo = new Contacts_info();
            return contactsInfo;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Info[position];
    }

    @Override
    public int getCount() {
        return NumbOfInfo;
    }
}
