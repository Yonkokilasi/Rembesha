package com.example.bubbles.rembesha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.bubbles.rembesha.adapters.FireBaseMakeupListAdapter;
import com.example.bubbles.rembesha.FirebaseMakeupViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SavedMakeupListActivity extends AppCompatActivity {
    private DatabaseReference mMakeupReference;
    private FireBaseMakeupListAdapter mFirebaseAdapter;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up_list);
        ButterKnife.bind(this);
        setUpFireBaseAdapter();
        Toast.makeText(SavedMakeupListActivity.this,"Tip :Swipe left to delete",Toast.LENGTH_SHORT).show();
        Toast.makeText(SavedMakeupListActivity.this,"Tip : You can rearrange them by dragging up or down",Toast.LENGTH_SHORT).show();
    }

    private void setUpFireBaseAdapter() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query query = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MAKEUP).child(uid).orderByChild(Constants.FIREBASE_QUERY_INDEX);
        mFirebaseAdapter = new FireBaseMakeupListAdapter(MakeUp.class,R.layout.makeup_list_item, FirebaseMakeupViewHolder.class,query, this,this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);

    }

}
