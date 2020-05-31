package com.example.photoclient.app;

import com.example.photoclient.model.retrofit.ApiRequest;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;

@Module
public class TestModule {
    @Provides
    public ApiRequest provideApiRequest() {
        return Mockito.mock(ApiRequest.class);
    }
}
