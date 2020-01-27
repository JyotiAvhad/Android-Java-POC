package com.example.recyclerviewwithcardview;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    LayoutInflater layoutInflater;
    List<RecyclerViewModel> modelArrayList;
    Context context;


    public RecyclerViewAdapter(Context ctx, List<RecyclerViewModel> modelRecyclerArrayList) {

//        this.context=ctx;
        layoutInflater=LayoutInflater.from(ctx);
        this.modelArrayList=modelRecyclerArrayList;
        context = ctx;
    }


    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=layoutInflater.inflate(R.layout.recyclerview_list,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view, context);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder holder, int position) {

        holder.id.setText(String.valueOf(modelArrayList.get(position).getId()));
        holder.fname.setText(modelArrayList.get(position).getFirst_name());
        holder.lname.setText(modelArrayList.get(position).getLast_name());
        holder.email.setText(modelArrayList.get(position).getEmail());
        //img using picasso
        Picasso.get()
                .load(modelArrayList.get(position).getAvatar())
                .into(holder.image);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView id,fname,lname,email;
        ImageView image;

        public MyViewHolder(@NonNull View itemView, Context context) {

            super(itemView);

            itemView.setOnClickListener(this);
            id=itemView.findViewById(R.id.tv_id);
            fname=itemView.findViewById(R.id.tv_firstName);
            lname=itemView.findViewById(R.id.tv_lastName);
            email=itemView.findViewById(R.id.tv_email);
            image=itemView.findViewById(R.id.imageView);
        }

        @Override
        public void onClick(View v) {

//            Log.d("Alpha","position="+getLayoutPosition());

            Toast.makeText(context,"You can Edit the data",Toast.LENGTH_SHORT).show();

            CustomDialogClass dialog = new CustomDialogClass(context, modelArrayList.get(getAdapterPosition()));
            dialog.show();
        }
    }
}
