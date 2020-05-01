package com.example.photoclient.presenter;

import com.example.photoclient.view.IViewHolder;

public interface RecyclerPresenter {
    void bindView(IViewHolder iViewHolder);
    int getItemCount();
    void getUrl(String url);
    void clearDatabase();
}
