package com.example.bubbles.rembesha.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.bubbles.rembesha.FirebaseMakeupViewHolder;
import com.example.bubbles.rembesha.MakeUp;
import com.example.bubbles.rembesha.SavedMakeupListActivity;
import com.example.bubbles.rembesha.ui.MakeupDetailActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import org.parceler.Parcels;

import java.util.ArrayList;

/**
 * Created by bubbles on 6/22/17.
 */

public class FireBaseMakeupListAdapter extends FirebaseRecyclerAdapter<MakeUp, FirebaseMakeupViewHolder> {
    private DatabaseReference mRef;
    private Context mContext;
    private ChildEventListener  mChildEventListener;
    private ArrayList<MakeUp> mProduct = new ArrayList<>();
    @Override
    protected void populateViewHolder(final FirebaseMakeupViewHolder viewHolder, MakeUp model, int position) {
        viewHolder.bindProduct(model);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MakeupDetailActivity.class);
                intent.putExtra("position",viewHolder.getAdapterPosition());
                intent.putExtra("makeup", Parcels.wrap(mProduct));
                mContext.startActivity(intent);
            }
        });
    }
    private void setIndexInFirebase() {
        for (MakeUp makeUp: mProduct) {
            int index = mProduct.indexOf(makeUp);
            DatabaseReference ref = getRef(index);
            makeUp.setIndex(Integer.toString(index));
            ref.setValue(makeUp);
        }
    }
    @Override
    public void cleanup() {
        super.cleanup();
        setIndexInFirebase();
        mRef.removeEventListener(mChildEventListener);
    }

    public FireBaseMakeupListAdapter(Class<MakeUp> modelClass, int modelLayout, Class<FirebaseMakeupViewHolder> viewHolderClass, Query ref, SavedMakeupListActivity savedMakeupListActivity, Context context) {
        super(modelClass,modelLayout,viewHolderClass,ref);
        mRef = ref.getRef();
        mContext = context;
        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mProduct.add(dataSnapshot.getValue(MakeUp.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
