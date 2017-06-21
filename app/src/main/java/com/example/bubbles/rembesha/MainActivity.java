package com.example.bubbles.rembesha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.brand) EditText mBrand;
    @Bind(R.id.product_category) EditText mCategory;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.product_type) EditText mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mSearchButton) {
            String brand = mBrand.getText().toString();
            String category = mCategory.getText().toString();
            String type = mType.getText().toString();
            Intent intent = new Intent(MainActivity.this,MakeUpListActivity.class);
            intent.putExtra("brand",brand);
            intent.putExtra("category",category);
            intent.putExtra("type",type);
            if (category.equals("")) {
                mType.setError("Please enter a category");
                return;
            } else {
                Toast.makeText(MainActivity.this,"Looking for your makeup.",Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        }
    }
}
