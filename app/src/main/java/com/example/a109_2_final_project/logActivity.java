package com.example.a109_2_final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;

public class logActivity extends AppCompatActivity {

    //參考04.5
    private final LinkedList<String> mTypeList = new LinkedList<>();
    private final LinkedList<String> mValueList = new LinkedList<>();
    private final LinkedList<String> mDescribeList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        for (int i = 0; i < 20; i++) {
            mTypeList.addLast("Type " + i);
            mValueList.addLast("Value"+i);
            mDescribeList.addLast("describe"+i);
        }

        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mTypeList, mValueList, mDescribeList);
        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}