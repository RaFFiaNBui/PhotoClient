package com.example.photoclient.model.gson;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "urls")
public class Hit {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @Expose
    @SerializedName("webformatURL")
    public String webformatURL;
}
