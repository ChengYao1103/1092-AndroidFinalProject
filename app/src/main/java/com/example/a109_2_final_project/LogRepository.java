package com.example.a109_2_final_project;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LogRepository {
    private LogDao logDao;
    private LiveData<List<Log>> mAllLogs;

    LogRepository(Application application){
        LogRoomDatabase db = LogRoomDatabase.getDatabase(application);
        logDao = db.noteDao();
        mAllLogs = logDao.getAllLogs();
    }

    LiveData<List<Log>> getAllNotes(){
        return mAllLogs;
    }
    LiveData<Log> getLog(int id){
        return logDao.getLog(id);
    }

    public void remove(Log log) {
        new RemoveAsyncTask(logDao).execute(log);
    }

    public void insert(Log log){
        new InsertAsyncTask(logDao).execute(log);
    }

    private static class InsertAsyncTask extends AsyncTask<Log, Void, Void> {

        private LogDao mAsyncTaskDao;

        InsertAsyncTask(LogDao logDao) {
            mAsyncTaskDao = logDao;
        }

        @Override
        protected Void doInBackground(final Log... logs) {
            mAsyncTaskDao.insert(logs[0]);
            return null;
        }
    }
    private static class RemoveAsyncTask extends AsyncTask<Log, Void, Void> {

        private LogDao mAsyncTaskDao;

        RemoveAsyncTask(LogDao logDao) {
            mAsyncTaskDao = logDao;
        }

        @Override
        protected Void doInBackground(final Log... logs) {
            mAsyncTaskDao.removeLog(logs[0].getId());
            return null;
        }
    }

}
