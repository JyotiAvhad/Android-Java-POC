package com.example.verticalrecyclerviewurlimages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {


    ArrayList<String> urls;
    Context context;

    public ImageAdapter(ArrayList<String> imgUrl, Context context) {

        this.urls = imgUrl;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_img_list_layout, parent, false);
        v.setLayoutParams(new RecyclerView.LayoutParams(1000,800));
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Glide.with(this.context)
                .load(urls.get(position))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.getImage());

    }

    @Override
    public int getItemCount() {
        return urls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img);
        }

        public ImageView getImage() {
            return this.image;
        }
    }
}
