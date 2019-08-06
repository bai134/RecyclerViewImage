package com.bai.textrecyclerviewimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TempRecyclerView mTempRecyclerView;
    private ImageView mImageView;
    private TempAdapter mTempAdapter;
    private List<Integer> mIntegerList,mIntegerList2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTempRecyclerView = findViewById(R.id.recyclerview);
        mImageView = findViewById(R.id.imageView);

        mIntegerList = new ArrayList<>();
        mIntegerList2 = new ArrayList<>();
        mIntegerList.add(0);
        mIntegerList.add(10);
        for (int i=0;i<=20;i++)
            mIntegerList2.add(i);

        mTempAdapter = new TempAdapter(this,mIntegerList2);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mTempRecyclerView.setLayoutManager(linearLayoutManager);
        mTempRecyclerView.setListPosition(mIntegerList);
        mTempRecyclerView.setImage(mImageView);
        mTempRecyclerView.setAdapter(mTempAdapter);
        mTempAdapter.SetClick(new AdapterOnClickListener() {
            @Override
            public void onClick(int position) {
                boolean flag = false;
                for (int i=0;i<mIntegerList.size();i++){
                    if (position == mIntegerList.get(i)) {
                        Toast.makeText(MainActivity.this,"透明item",Toast.LENGTH_SHORT).show();
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    Toast.makeText(MainActivity.this,"标题item",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
