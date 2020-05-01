package com.example.photoclient.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.photoclient.R;
import com.example.photoclient.model.ErrorLoadingListener;
import com.example.photoclient.model.PicassoLoader;
import com.example.photoclient.presenter.RecyclerPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private RecyclerPresenter recyclerPresenter;
    private PicassoLoader picassoLoader;

    //package-private
    MyAdapter(RecyclerPresenter iRecyclerMainPresenter) {
        this.recyclerPresenter = iRecyclerMainPresenter;
        this.picassoLoader = new PicassoLoader();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_three, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {
        holder.position = position;
        recyclerPresenter.bindView(holder);
    }

    @Override
    public int getItemCount() {
        return recyclerPresenter.getItemCount();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements IViewHolder {

        private int position = 0;
        private String url = "";

        private final ErrorLoadingListener errorLoadingListener = new ErrorLoadingListener() {
            @Override
            public void onErrorLoading() {
                recyclerPresenter.clearDatabase();
            }
        };

        @BindView(R.id.image_view)
        ImageView imageView;

        //package-private
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            imageView.setOnClickListener(v -> recyclerPresenter.getUrl(url));
        }

        @Override
        public void setImage(String url) {
            this.url = url;
            picassoLoader.loadImage(url, imageView, errorLoadingListener);
        }

        @Override
        public int getPos() {
            return position;
        }
    }
}
