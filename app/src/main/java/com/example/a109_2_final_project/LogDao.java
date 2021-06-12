package com.example.a109_2_final_project;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface LogDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Log log);

    @Query("SELECT * FROM Log ORDER BY id ASC")
    LiveData<List<Log>> getAllLogs();

    @Query("SELECT * FROM Log WHERE id = :id")
    LiveData<Log> getLog(int id);

    @Query("DELETE FROM Log WHERE id = :id")
    void removeLog(int id);

}
