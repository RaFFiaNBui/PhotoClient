package com.example.photoclient.app;

import com.example.photoclient.presenter.ThreePresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {TestModule.class})
public interface TestComponent {
    void inject(ThreePresenter threePresenter);
}
