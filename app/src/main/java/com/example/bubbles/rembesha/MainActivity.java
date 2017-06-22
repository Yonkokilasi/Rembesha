package com.example.bubbles.rembesha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bubbles.rembesha.ui.BeginingActivity;
import com.example.bubbles.rembesha.ui.MakeUpListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    @Bind(R.id.brand) EditText mBrand;
    @Bind(R.id.product_category) EditText mCategory;
    @Bind(R.id.searchButton) Button mSearchButton;
    @Bind(R.id.product_type) EditText mType;
    private DatabaseReference mSearchedBrandReference;
    @Bind(R.id.saveButton) Button mSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedBrandReference = FirebaseDatabase.getInstance().getReference().child(Constants.FIREBASE_CHILD_SEARCHED_MAKEUP);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSearchButton.setOnClickListener(this);
        mSaved.setOnClickListener(this);
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
                
            saveBrandToFirebase(brand);
                startActivity(intent);
        }
        if (v == mSaved) {
            Intent intent = new Intent(MainActivity.this,SavedMakeupListActivity.class);
            startActivity(intent);
        }
    }

    private void saveBrandToFirebase(String brand) {
        mSearchedBrandReference.push().setValue(brand);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this,BeginingActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        Toast.makeText(MainActivity.this,"You have been successfully logged out",Toast.LENGTH_SHORT);
        startActivity(intent);
        finish();
    }
}
