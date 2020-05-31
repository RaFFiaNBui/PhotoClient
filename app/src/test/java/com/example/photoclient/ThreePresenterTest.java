package com.example.photoclient;

import com.example.photoclient.app.DaggerTestComponent;
import com.example.photoclient.app.TestComponent;
import com.example.photoclient.app.TestModule;
import com.example.photoclient.model.gson.Hit;
import com.example.photoclient.model.gson.Photo;
import com.example.photoclient.model.retrofit.ApiRequest;
import com.example.photoclient.presenter.ThreePresenter;
import com.example.photoclient.view.MoxyView;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.schedulers.Schedulers;

/*
* Для этого теста необходимо закоментировать в презентере строки:
* this.urlDao = App.getAppDatabase().urlDao();
* loadDatabase();
* saveToDatabase();
 */
@RunWith(MockitoJUnitRunner.class)
public class ThreePresenterTest {

    private ThreePresenter threePresenter;

    @Mock
    MoxyView moxyView;

    @BeforeClass
    public static void setupClass() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __ -> Schedulers.trampoline());
    }

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        threePresenter = Mockito.spy(new ThreePresenter());
    }

    @Test
    public void getAllPhotoIsCorrect() {
        TestComponent component = DaggerTestComponent.builder()
                .testModule(new TestModule() {

                    @Override
                    public ApiRequest provideApiRequest() {
                        ApiRequest apiRequest = super.provideApiRequest();
                        Photo photo = new Photo();
                        List<Hit> list = new ArrayList<>();
                        Hit hit = new Hit();
                        hit.webformatURL = "Myurl";
                        list.add(hit);
                        photo.hits = list;
                        Mockito.when(apiRequest.requestServer()).thenReturn(Observable.just(photo));
                        return apiRequest;
                    }
                }).build();
        component.inject(threePresenter);
        threePresenter.attachView(moxyView);
        threePresenter.getAllPhoto();
        Mockito.verify(moxyView).updateRecyclerView();
    }
}
