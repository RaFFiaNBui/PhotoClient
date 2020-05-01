package com.example.photoclient.model;

import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PicassoLoader {

    public void loadImage(String url, ImageView imageView, ErrorLoadingListener errorLoadingListener) {
        Picasso
                .get()
                .load(url)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.e("MyTag", "onError: ", e);
                        errorLoadingListener.onErrorLoading();
                    }
                });
    }
}
