package com.example.photoclient.presenter;

import android.util.Log;

import com.example.photoclient.view.DetailMoxyView;

import moxy.InjectViewState;
import moxy.MvpPresenter;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailMoxyView> {

    private static final String TAG = "MyTag";

    public void showPosition(int position) {
        Log.d(TAG, "DetailPresenter.showPosition: " + position);
    }
}
