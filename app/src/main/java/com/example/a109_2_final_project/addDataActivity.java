package com.example.a109_2_final_project;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class addDataActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner mSpin;
    private int mType;
    private Spinner spinner_type;
    private EditText value;
    private EditText description;
    private LogViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);

        //參考04.2內容
        mSpin =findViewById(R.id.spinner_type);
        if (mSpin != null) {
            mSpin.setOnItemSelectedListener(this);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.labels_array,
                android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (mSpin != null) {
            mSpin.setAdapter(adapter);
        }
        spinner_type = findViewById(R.id.spinner_type);
        value = findViewById(R.id.value);
        description = findViewById(R.id.description);
        viewModel = ViewModelProviders.of(this).get(LogViewModel.class);
    }

    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //參考04.2內容
        String spinnerLabel = parent.getItemAtPosition(position).toString();
        displayToast(spinnerLabel);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //參考04.2內容
    }

    public void cancelOnclick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //參考https://www.itread01.com/content/1550323443.html
        startActivity(intent);
    }

    public void saveOnclick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        int valueint = Integer.parseInt(value.getText().toString());
        Log logx = new Log(0, spinner_type.getSelectedItemPosition(), valueint, description.getText().toString());
        viewModel.insert(logx);
        this.testLog();
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //參考https://www.itread01.com/content/1550323443.html
        startActivity(intent);
    }

    public void testLog() {
        viewModel.getAllLogs().observe(this, new Observer<List<Log>>() {
            @Override
            public void onChanged(List<Log> logs) {
                for(Log l : logs) {
                    android.util.Log.d("info", l.getId() + ":: Type" + l.getType() + "  Value" + l.getValue() + "  Desc::" + l.getDescription());
                }
            }
        });
    }
}