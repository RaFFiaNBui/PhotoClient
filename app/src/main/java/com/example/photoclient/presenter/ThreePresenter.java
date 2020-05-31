package com.example.photoclient.presenter;

import android.util.Log;

import com.example.photoclient.model.daggerApp.App;
import com.example.photoclient.model.gson.Hit;
import com.example.photoclient.model.gson.Photo;
import com.example.photoclient.model.retrofit.ApiRequest;
import com.example.photoclient.model.room.UrlDao;
import com.example.photoclient.view.IViewHolder;
import com.example.photoclient.view.MoxyView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class ThreePresenter extends MvpPresenter<MoxyView> implements RecyclerPresenter {

    private static final String TAG = "MyTag";

    private List<Hit> hitList;
    private UrlDao urlDao;
    private boolean isRunning = false;

    @Inject
    ApiRequest apiRequest;

    public ThreePresenter() {
        this.urlDao = App.getAppDatabase().urlDao();
    }

    @Override
    protected void onFirstViewAttach() {
        loadDatabase();
    }

    public void getAllPhoto() {
        Observable<Photo> single = apiRequest.requestServer();

        Disposable disposable = single.observeOn(AndroidSchedulers.mainThread()).subscribe(photo -> {
                    hitList = photo.hits;
                    Log.d(TAG, "ThreePresenter.loadDatabase: Загрузка по api успешна");
                    saveToDatabase();
                    getViewState().updateRecyclerView();
                }, throwable -> Log.d(TAG, "ThreePresenter.getAllPhoto: Error" + throwable)
        );
    }

    private void saveToDatabase() {
        Disposable disposable1 = urlDao.addUrlList(hitList).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(webformatURL -> Log.d(TAG, "ThreePresenter.saveToDatabase: Запись в базу данных успешна"),
                        throwable -> Log.e(TAG, "ThreePresenter.saveToDatabase: Ошибка записи в БД ", throwable));
    }

    private void loadDatabase() {
        Disposable disposable1 = urlDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    if (list.size() == 0) {
                        getAllPhoto();
                    } else {
                        Log.d(TAG, "ThreePresenter.loadDatabase: Данные из БД загружены");
                        this.hitList = list;
                        getViewState().updateRecyclerView();
                    }
                }, throwable -> Log.e(TAG, "loadDatabase: Ошибка чтения БД ", throwable));

    }

    @Override
    public void bindView(IViewHolder holder) {
        holder.setImage(hitList.get(holder.getPos()).webformatURL);
    }

    @Override
    public int getItemCount() {
        if (hitList != null) {
            return hitList.size();
        }
        return 0;
    }

    @Override
    public void getUrl(String url) {
        getViewState().showPicture(url);
    }

    @Override
    public void clearDatabase() {
        if (!isRunning) {
            isRunning = true;
            Disposable disposable = urlDao.deleteAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(urlList -> {
                        Log.d(TAG, "clearDatabase: База данных очищена");
                        getAllPhoto();
                    }, throwable -> Log.e(TAG, "clearDatabase: Ошибка очистки базы данных", throwable));
        }
    }
}
