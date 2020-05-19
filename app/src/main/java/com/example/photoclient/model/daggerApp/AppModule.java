package com.example.photoclient.model.daggerApp;

import android.app.Application;

import com.example.photoclient.model.PicassoLoader;
import com.example.photoclient.model.retrofit.ApiRequest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    ApiRequest provideApiRequest() {
        return new ApiRequest();
    }

    @Singleton
    @Provides
    PicassoLoader providePicassoLoader() {
        return new PicassoLoader();
    }
}
