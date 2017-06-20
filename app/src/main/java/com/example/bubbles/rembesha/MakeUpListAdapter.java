package com.example.bubbles.rembesha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by bubbles on 6/20/17.
 */

public class MakeUpListAdapter extends RecyclerView.Adapter<MakeUpListAdapter.MakeUpViewHolder> {
    private Context mContext;
    private ArrayList<MakeUp>mMakeUp = new ArrayList<>();

    public MakeUpListAdapter(Context context, ArrayList<MakeUp> makeUps) {
        mContext = context;
        mMakeUp = makeUps;
    }

    public class MakeUpViewHolder {
    }
}
