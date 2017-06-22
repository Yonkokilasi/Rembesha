package com.example.bubbles.rembesha.ui;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.bubbles.rembesha.adapters.MakeupPagerAdapter;

import org.parceler.Parcels;
import com.example.bubbles.rembesha.R;
import com.example.bubbles.rembesha.MakeUp;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MakeupDetailActivity extends AppCompatActivity {
    @Bind(R.id.viewPager) ViewPager mViewPager;
    private MakeupPagerAdapter adapterViewPager;
    ArrayList<MakeUp> mMakeup = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up_detail);
        ButterKnife.bind(this);
        mMakeup = Parcels.unwrap(getIntent().getParcelableExtra("makeup"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new MakeupPagerAdapter(getSupportFragmentManager(),mMakeup);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}
