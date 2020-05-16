package com.example.photoclient.model.daggerApp;

import android.app.Application;

import androidx.room.Room;

import com.example.photoclient.model.room.AppDatabase;

public class App extends Application {

    private static AppComponent appComponent;
    private static AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = generateAppComponent();

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "urls_database").build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public static AppDatabase getAppDatabase() {
        return appDatabase;
    }

    public AppComponent generateAppComponent() {
        return DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
    }
}
