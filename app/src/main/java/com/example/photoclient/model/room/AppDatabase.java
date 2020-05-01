package com.example.photoclient.model.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.photoclient.model.gson.Hit;

@Database(entities = {Hit.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UrlDao urlDao();
}
