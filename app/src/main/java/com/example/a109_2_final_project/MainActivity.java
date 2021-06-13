package com.example.a109_2_final_project;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LogViewModel viewModel;
    private List<Log> logs;
    TextView totalTotal, totalPlus, totalMinus;
    private Button mBtn1, mBtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = findViewById(R.id.fab);
        Intent intent = new Intent(this, addDataActivity.class);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
        <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom|end"
        android:layout_margin="@dimen/fab_margin"
        app:srcCompat="@android:drawable/ic_menu_add" />*/

        viewModel = ViewModelProviders.of(this).get(LogViewModel.class);
        totalTotal = findViewById(R.id.total_total);
        totalPlus = findViewById(R.id.total_plus);
        totalMinus = findViewById(R.id.total_minus);
        mBtn1 = findViewById(R.id.button_addData);
        mBtn2 = findViewById(R.id.button_history);
        this.initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_count) {
            Intent intent = new Intent(this, countActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public void initData() {
        MainActivity self = this;
        viewModel.getAllLogs().observe(this, new Observer<List<Log>>() {
            @Override
            public void onChanged(List<Log> logs) {
                self.logs = logs;
                self.initTotalSum();
                self.initTotalPlus();
                self.initTotalMinus();
            }
        });
    }

    public void initTotalSum() {
        int amount = 0;
        for(Log log : this.logs) {
            amount += log.getValue();
        }
        totalTotal.setText(amount + "");
    }

    public void initTotalPlus() {
        int amount = 0;
        for(Log log : this.logs) {
            if (log.getValue() > 0) {
                amount += log.getValue();
            }
        }
        totalPlus.setText(amount + "");
    }

    public void initTotalMinus() {
        int amount = 0;
        for(Log log : this.logs) {
            if (log.getValue() < 0) {
                amount += -log.getValue();
            }
        }
        totalMinus.setText(amount + "");
    }

    public void addBtnClicked(View view){
        Intent intent = new Intent(this, addDataActivity.class);
        startActivity(intent);
    }

    public void historyBtnClicked(View view){
        Intent intent = new Intent(this, logActivity.class);
        startActivity(intent);
    }
}