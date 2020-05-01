package com.example.photoclient.view;

import moxy.MvpView;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface DetailMoxyView extends MvpView {
    @StateStrategyType(value = AddToEndSingleStrategy.class)
    void empty();
}
