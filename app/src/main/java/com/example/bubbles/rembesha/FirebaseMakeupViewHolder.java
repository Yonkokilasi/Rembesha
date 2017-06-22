package com.example.bubbles.rembesha;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by bubbles on 6/22/17.
 */

public class FirebaseMakeupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    View mView;
    Context mContext;


    public FirebaseMakeupViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindProduct(MakeUp makeUp) {
        ImageView productImageView = (ImageView) mView.findViewById(R.id.imageView);
        TextView nameTextView = (TextView) mView.findViewById(R.id.nameTextView);
        TextView categoryTextView = (TextView) mView.findViewById(R.id.categoryTextView);
        TextView ratingTextView = (TextView) mView.findViewById(R.id.ratingTextView);
        TextView brandTextView = (TextView) mView.findViewById(R.id.brandTextView);
        Picasso.with(mContext).load(makeUp.getImageUrl()).into(productImageView);
        nameTextView.setText(makeUp.getName());
        categoryTextView.setText(makeUp.getCategory());
        brandTextView.setText("By "+makeUp.getBrand());
        ratingTextView.setText(makeUp.getRating()+"/5");
    }

    @Override
    public void onClick(View v) {

    }
}
