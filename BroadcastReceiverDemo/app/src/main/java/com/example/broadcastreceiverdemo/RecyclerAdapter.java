package com.example.broadcastreceiverdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>{

    private ArrayList<IncomingNumber> arrayList=new ArrayList<>();

    public RecyclerAdapter(ArrayList<IncomingNumber> numberArrayList){

        this.arrayList=numberArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.id.setText(Integer.toString(arrayList.get(position).getId()));
        holder.number.setText(arrayList.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView id,number;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id=itemView.findViewById(R.id.txtId);
            number=itemView.findViewById(R.id.txtNumber);
        }
    }
}
