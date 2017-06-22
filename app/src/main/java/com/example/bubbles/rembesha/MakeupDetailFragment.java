package com.example.bubbles.rembesha;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcel;
import org.parceler.Parcels;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by bubbles on 6/21/17.
 */

public class MakeupDetailFragment extends Fragment implements View.OnClickListener {
    @Bind(R.id.nameTextView) TextView mNameTextView;
    @Bind(R.id.brandTextView) TextView mBrandTextView;
    @Bind(R.id.productImageView) ImageView mImageView;
    @Bind(R.id.colorTextView) TextView mColorTextView;
    @Bind(R.id.descriptionTextView) TextView mDescriptionTextView;
    @Bind(R.id.priceView) TextView mPriceView;
    @Bind(R.id.websiteLink)TextView mWebsiteView;
    @Bind(R.id.ratingTextView) TextView mRating;
    @Bind(R.id.categoryTextView) TextView mCategoryView;
    private MakeUp mMakeup;
    @Bind(R.id.saveMakeup) Button mSaveMakeUp;


public static MakeupDetailFragment newInstance(MakeUp makeUp) {
    MakeupDetailFragment makeupDetailFragment = new MakeupDetailFragment();
    Bundle args = new Bundle();
    args.putParcelable("makeup", Parcels.wrap(makeUp));
    makeupDetailFragment.setArguments(args);
    return makeupDetailFragment;
}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMakeup = Parcels.unwrap(getArguments().getParcelable("makeup"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_makeup_detail,container,false);
        ButterKnife.bind(this,view);

        Picasso.with(view.getContext()).load(mMakeup.getImageUrl()).into(mImageView);
        mNameTextView.setText(mMakeup.getName());
        mBrandTextView.setText("By "+ mMakeup.getBrand());
        mPriceView.setText(mMakeup.getPrice()+"$");
        mRating.setText(mMakeup.getRating()+"/5.0");
        mColorTextView.setText("Colors available"+android.text.TextUtils.join(",",mMakeup.getColors()));
        mWebsiteView.setOnClickListener(this);
        mSaveMakeUp.setOnClickListener(this);
        mCategoryView.setText("Category"+ mMakeup.getCategory());
        mDescriptionTextView.setText(mMakeup.getDescription());
        mSaveMakeUp.setOnClickListener(this);
        return view;

    }


    @Override
    public void onClick(View v) {
    if (v == mWebsiteView) {
        Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mMakeup.getWebsiteLink()));
        startActivity(webIntent);
    }
    if (v == mSaveMakeUp) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        DatabaseReference makeupReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MAKEUP).child(uid);
        DatabaseReference pushRef = makeupReference.push();
        String pushId = pushRef.getKey();
        mMakeup.setPushId(pushId);
        pushRef.setValue(mMakeup);
        Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
    }


    }
}
