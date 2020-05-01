package com.example.photoclient.model.retrofit;

import com.example.photoclient.model.gson.Photo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api")
    Observable<Photo> getPhoto(@Query("key") String key);
}
