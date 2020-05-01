package com.example.photoclient.view;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.photoclient.R;
import com.example.photoclient.model.ErrorLoadingListener;
import com.example.photoclient.model.PicassoLoader;
import com.example.photoclient.presenter.DetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import moxy.MvpAppCompatActivity;
import moxy.presenter.InjectPresenter;

public class DetailActivity extends MvpAppCompatActivity implements DetailMoxyView {

    private static final String URL = "url";

    @InjectPresenter
    DetailPresenter detailPresenter;

    @BindView(R.id.detail_img)
    ImageView imageView;

    private final ErrorLoadingListener errorLoadingListener = () -> {
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        String url = getIntent().getStringExtra(URL);

        new PicassoLoader().loadImage(url, imageView, errorLoadingListener);
    }

    @Override
    public void empty() {
    }
}
