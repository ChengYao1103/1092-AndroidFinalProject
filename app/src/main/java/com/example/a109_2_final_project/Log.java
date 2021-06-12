package com.example.a109_2_final_project;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;

import java.util.Date;

@Entity(tableName = "log")
public class Log {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @NonNull
    private int type;

    @NonNull
    private int value;

    @NonNull
    private String description;

    private Date createAt;

    public Log(int id, @NonNull int type, @NonNull int value, @NonNull String description, @NonNull Date createAt){
        this.id = id;
        this.type = type;
        this.value = value;
        this.description = description;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    @NonNull
    public String getDescription() {
        return description;
    }
    public void setDescription(@NonNull String description) {
        this.description = description;
    }
    @NonNull
    public int getType() {
        return type;
    }
    @NonNull
    public int getValue() {
        return value;
    }
    public void setType(@NonNull int type) {
        this.type = type;
    }
    public void setValue(@NonNull int value) {
        this.value = value;
    }
    public Date getCreateAt() {
        return createAt;
    }
    public void setCreateAt(@NonNull Date date) {
        this.createAt = date;
    }
}
