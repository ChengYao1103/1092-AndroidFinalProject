package com.example.a109_2_final_project;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class LogViewModel extends AndroidViewModel {

    private LogRepository mRepository;
    private LiveData<List<Log>> mAllLogs;

    public LogViewModel(@NonNull Application application) {
        super(application);
        mRepository = new LogRepository(application);
        mAllLogs = mRepository.getAllNotes();
    }

    LiveData<List<Log>> getAllLogs() {
        return mAllLogs;
    }
    LiveData<Log> getLog(int id) {
        return mRepository.getLog(id);
    }
    public void remove(Log log) {
        mRepository.remove(log);
    }
    public void insert(Log Log) {
        mRepository.insert(Log);
    }
}
