package com.example.photoclient.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface MoxyView extends MvpView {

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void showPicture(String url);

    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void updateRecyclerView();
}
