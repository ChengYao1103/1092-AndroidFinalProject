package com.example.a109_2_final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class countActivity extends AppCompatActivity {

    private TextView[] viewArrayPlus, viewArrayMinus;
    private LogViewModel viewModel;
    private List<Log> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        viewArrayPlus = new TextView[8];
        viewArrayMinus = new TextView[8];
        setArray();

        viewModel = ViewModelProviders.of(this).get(LogViewModel.class);
        this.initData();

    }

    public void initData() {
        countActivity self = this;
        viewModel.getAllLogs().observe(this, new Observer<List<Log>>() {
            @Override
            public void onChanged(List<Log> logs) {
                self.logs = logs;
                self.initTotalSum();
            }
        });
    }

    public void initTotalSum() {
        int mType, mValue;
        for (Log log : this.logs) {
            mType = log.getType();
            if (log.getValue() > 0) {
                mValue = Integer.parseInt(viewArrayPlus[mType].getText().toString());
                mValue = mValue + log.getValue();
                viewArrayPlus[mType].setText(String.valueOf(mValue));
            } else {
                mValue = Integer.parseInt(viewArrayMinus[mType].getText().toString());
                mValue = mValue - log.getValue();
                viewArrayMinus[mType].setText(String.valueOf(mValue));
            }
        }
    }



    private void setArray(){
        viewArrayPlus[0] = findViewById(R.id.textView0_plus);
        viewArrayMinus[0] = findViewById(R.id.textView0_minus);
        viewArrayPlus[1] = findViewById(R.id.textView1_plus);
        viewArrayMinus[1] = findViewById(R.id.textView1_minus);
        viewArrayPlus[2] = findViewById(R.id.textView2_plus);
        viewArrayMinus[2] = findViewById(R.id.textView2_minus);
        viewArrayPlus[3] = findViewById(R.id.textView3_plus);
        viewArrayMinus[3] = findViewById(R.id.textView3_minus);
        viewArrayPlus[4] = findViewById(R.id.textView4_plus);
        viewArrayMinus[4] = findViewById(R.id.textView4_minus);
        viewArrayPlus[5] = findViewById(R.id.textView5_plus);
        viewArrayMinus[5] = findViewById(R.id.textView5_minus);
        viewArrayPlus[6] = findViewById(R.id.textView6_plus);
        viewArrayMinus[6] = findViewById(R.id.textView6_minus);
        viewArrayPlus[7] = findViewById(R.id.textView7_plus);
        viewArrayMinus[7] = findViewById(R.id.textView7_minus);
    }

}