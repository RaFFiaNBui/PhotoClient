package com.example.photoclient.model.retrofit;

import android.util.Log;

import com.example.photoclient.model.gson.Photo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiRequest {

    public Observable<Photo> requestServer() {

        Gson gson = new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .create();

        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        ApiService api = new Retrofit.Builder()
                .baseUrl("https://pixabay.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build()
                .create(ApiService.class);
        Log.d("MyTag", "ApiRequest.requestServer: выполняется запрос на сервер");

        return api.getPhoto("9250926-552b631cddef606bad3e807d2").subscribeOn(Schedulers.io());
    }
}
