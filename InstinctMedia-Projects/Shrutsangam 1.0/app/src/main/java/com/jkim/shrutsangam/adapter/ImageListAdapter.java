package com.jkim.shrutsangam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.api.modal.ImageListResponse;
import com.jkim.shrutsangam.utils.interfaces.OnDeleteImageClick;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ImageListHolder> {
    private List<ImageListResponse.imageListDataResponse> listDataResponses;
    private Context context;
    private OnDeleteImageClick onDeleteImageClick;

    public ImageListAdapter(List<ImageListResponse.imageListDataResponse> listDataResponses, Context context, OnDeleteImageClick onDeleteImageClick) {
        this.listDataResponses = listDataResponses;
        this.context = context;
        this.onDeleteImageClick = onDeleteImageClick;
    }

    @Override
    public ImageListAdapter.ImageListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ImageListAdapter.ImageListHolder(LayoutInflater.from(context).inflate(R.layout.row_image_list, parent, false));
    }

    @Override
    public void onBindViewHolder(final ImageListAdapter.ImageListHolder holder, final int position) {

        holder.txtBhandarName.setText(listDataResponses.get(position).getTitle());
        if (listDataResponses.get(position).getBookImage() != null || !listDataResponses.get(position).getBookImage().equals("")) {
            Picasso.get().
                    load(listDataResponses.get(position).getBookImage()).
                    error(R.drawable.ic_broken_image_black_24dp).
                    resize(200, 200)
                    .centerInside()
                    .into(holder.imgBookImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            holder.progressImgList.setVisibility(View.GONE);
                        }

                        @Override
                        public void onError(Exception e) {
                            holder.progressImgList.setVisibility(View.GONE);
                        }
                    });
        } else holder.progressImgList.setVisibility(View.GONE);

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteImageClick.onDeleteImageClick(listDataResponses.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return listDataResponses.size();
    }


    public class ImageListHolder extends RecyclerView.ViewHolder {
        private TextView txtBhandarName;
        private ProgressBar progressImgList;
        private ImageView imgBookImage, imgDelete;

        public ImageListHolder(View itemView) {
            super(itemView);
            txtBhandarName = itemView.findViewById(R.id.txt_bhandar_name);
            progressImgList = itemView.findViewById(R.id.progress_img_list);
            imgBookImage = itemView.findViewById(R.id.img_book_image);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }

    }
}
