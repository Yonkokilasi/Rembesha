package com.example.bubbles.rembesha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    public MakeUpViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.makeup_list_item,parent,false);
        MakeUpViewHolder viewHolder = new MakeUpViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(MakeUpViewHolder holder, int position) {
        holder.bindMakeUp(mMakeUp.get(position));
    }

    @Override
    public int getItemCount() {
        return mMakeUp.size();
    }
    public class MakeUpViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.nameTextView) TextView mName;
        @Bind(R.id.categoryTextView) TextView mCategory;
        @Bind(R.id.ratingTextView) TextView mRating;
        @Bind(R.id.brandTextView) TextView mBrand;
        @Bind(R.id.imageView) ImageView mImageView;
        private Context mContext;

        public MakeUpViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindMakeUp(MakeUp makeUp) {
            Picasso.with(mContext).load(makeUp.getImageUrl()).into(mImageView);
            mName.setText(makeUp.getName());
            mRating.setText(makeUp.getRating()+"/5");
            mCategory.setText("Category : "+makeUp.getCategory());
            mBrand.setText("By "+makeUp.getBrand());
        }

        @Override
        public void onClick(View v) {


        }
    }

}
