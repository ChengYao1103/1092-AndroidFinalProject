package com.example.a109_2_final_project;

import androidx.room.TypeConverter;

import java.util.Date;

public class DateConverters {
    @TypeConverter
    public static Date toCreateAt(Long dateLong){
        return dateLong == null ? null: new Date(dateLong);
    }

    @TypeConverter
    public static Long fromCreateAt(Date date){
        return date == null ? null : date.getTime();
    }
}
