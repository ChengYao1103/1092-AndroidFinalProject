package com.example.a109_2_final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class logActivity extends AppCompatActivity {

    //參考04.5
    private LinkedList<String> mTypeList = new LinkedList<>();
    private LinkedList<String> mValueList = new LinkedList<>();
    private LinkedList<String> mDescribeList = new LinkedList<>();
    private LinkedList<String> mDateList = new LinkedList<>();
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private LogViewModel viewModel;
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    private List<Log> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this).get(LogViewModel.class);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);
        this.initList();
        Toast.makeText(getApplicationContext(), "左右滑動以移除該項目", Toast.LENGTH_SHORT).show();
    }

    public void initList() {
        String[] typeName = getResources().getStringArray(R.array.labels_array);
        logActivity self = this;
        viewModel.getAllLogs().observe(this, new Observer<List<Log>>() {
            @Override
            public void onChanged(List<Log> logs) {
                self.logs = logs;
                mTypeList = new LinkedList<>();
                mValueList = new LinkedList<>();
                mDescribeList = new LinkedList<>();
                mDateList = new LinkedList<>();
                for(Log l : logs) {
                    mTypeList.addLast(typeName[l.getType()]);
                    mValueList.addLast((l.getValue() > 0 ? "+" : "") + l.getValue());
                    mDescribeList.addLast(l.getDescription());
                    mDateList.addLast(dateFormat.format(l.getCreateAt()));
                }
                // Get a handle to the RecyclerView.
                mRecyclerView = findViewById(R.id.recyclerview);
                // Create an adapter and supply the data to be displayed.
                mAdapter = new WordListAdapter(self, mTypeList, mValueList, mDescribeList, mDateList);
                // Connect the adapter with the RecyclerView.
                mRecyclerView.setAdapter(mAdapter);
                // Give the RecyclerView a default layout manager.
                mRecyclerView.setLayoutManager(new LinearLayoutManager(self));
                self.initSwiper();
            }
        });
    }

    public void initSwiper() {
        logActivity self = this;
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                mAdapter.notifyItemMoved(from, to);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //mSportsData.remove(viewHolder.getAdapterPosition());
                int pos = viewHolder.getAdapterPosition();
                android.util.Log.d("info", "onSwiped::" + pos);
                mAdapter.notifyItemRemoved(pos);
                Log log = self.logs.get(pos);
                self.logs.remove(pos);
                viewModel.remove(log);
            }
        });
        helper.attachToRecyclerView(mRecyclerView);
    }
}