package com.example.photoclient.model.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.photoclient.model.gson.Hit;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UrlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Single<List<Long>> addUrlList(List<Hit> urlList);

    @Query("SELECT * FROM urls")
    Single<List<Hit>> getAll();

    @Query("DELETE FROM urls")
    Single<Integer> deleteAll();
}
