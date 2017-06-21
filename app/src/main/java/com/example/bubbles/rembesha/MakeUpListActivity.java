package com.example.bubbles.rembesha;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MakeUpListActivity extends AppCompatActivity {
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private MakeUpListAdapter mAdapter;
    public static final String TAG = MakeUpListActivity.class.getSimpleName();
    public ArrayList<MakeUp> mMakeUp = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_up_list);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String brand = intent.getStringExtra("brand");
        String category = intent.getStringExtra("category");
        String type = intent.getStringExtra("type");
        getMakeUp(brand,category,type);
    }
    private void getMakeUp(final String brand, final String category, final String type) {
        final  MakeupService makeupService = new MakeupService();
        MakeupService.findMakeUp(brand, category, type, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Toast.makeText(MakeUpListActivity.this,"Check your internet connection",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
            mMakeUp = makeupService.processResults(response);
                MakeUpListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(mMakeUp.isEmpty()) {
                            Toast.makeText(MakeUpListActivity.this,"No results found for " + brand +" " + category+ ""+ type +" try another combination",Toast.LENGTH_LONG).show();
                        } else {
                            mAdapter = new MakeUpListAdapter(getApplicationContext(),mMakeUp);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MakeUpListActivity.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);
                        }
                    }
                });
            }
        });

    }
}
