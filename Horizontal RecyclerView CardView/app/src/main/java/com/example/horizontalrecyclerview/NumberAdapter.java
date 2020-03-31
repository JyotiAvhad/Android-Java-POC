package com.example.horizontalrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class NumberAdapter extends RecyclerView.Adapter<NumberAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<NumberModel> numberModelArrayList;


    public NumberAdapter(Context ctx, ArrayList<NumberModel> numberModelArrayList){

        inflater = LayoutInflater.from(ctx);
        this.numberModelArrayList = numberModelArrayList;
    }

    @Override
    public NumberAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_list_view, parent, false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(NumberAdapter.MyViewHolder holder, int position) {

        holder.iv.setImageResource(numberModelArrayList.get(position).getImage_drawable());
        holder.time.setText(numberModelArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return numberModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView time;
        ImageView iv;

        public MyViewHolder(View itemView) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);
        }

    }
}
