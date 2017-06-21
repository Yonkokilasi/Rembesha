package com.example.bubbles.rembesha.adapters;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import com.example.bubbles.rembesha.MakeUp;
import com.example.bubbles.rembesha.MakeupDetailFragment;

/**
 * Created by bubbles on 6/21/17.
 */

public class MakeupPagerAdapter extends FragmentPagerAdapter {
 private ArrayList<MakeUp> mMakeup;

    public MakeupPagerAdapter(FragmentManager fm, ArrayList<MakeUp> makeUps) {
        super(fm);
        mMakeup = makeUps;
    }


    @Override
    public Fragment getItem(int position) {
        return MakeupDetailFragment.newInstance(mMakeup.get(position));
    }

    @Override
    public int getCount() {
        return mMakeup.size();
    }
    @Override
    public CharSequence getPageTitle (int position) {
        return mMakeup.get(position).getName();
    }
}
