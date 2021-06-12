package com.example.a109_2_final_project;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Calendar;
import java.util.Date;

@Database(entities = {Log.class}, version = 2, exportSchema = false)
@TypeConverters({DateConverters.class})
abstract class LogRoomDatabase extends RoomDatabase {

    abstract LogDao logDao();

    private static LogRoomDatabase INSTANCE;

    static LogRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LogRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LogRoomDatabase.class, "log_database")
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PopulateDbAsync(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LogDao mDao;
        private int type = 0;
        private int value = 0;
        private String description = "description";
        private Date createAt = Calendar.getInstance().getTime();

        PopulateDbAsync(LogRoomDatabase db) {
            mDao = db.logDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            Log log = new Log(0, type, value, description, createAt);
            //mDao.insert(log);
            return null;
        }
    }

}
