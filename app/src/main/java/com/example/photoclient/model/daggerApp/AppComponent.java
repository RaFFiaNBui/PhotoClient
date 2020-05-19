package com.example.photoclient.model.daggerApp;

import com.example.photoclient.presenter.ThreePresenter;
import com.example.photoclient.view.DetailActivity;
import com.example.photoclient.view.MyAdapter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    void inject(ThreePresenter threePresenter);
    void inject(DetailActivity detailActivity);
    void inject(MyAdapter myAdapter);
}
