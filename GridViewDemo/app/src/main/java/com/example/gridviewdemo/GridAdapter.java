package com.example.gridviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

    private int icon[];
    private String name[];
    private Context context;
    private LayoutInflater inflater;


    public GridAdapter(Context context, int icon[], String name[]) {
        this.icon = icon;
        this.context = context;
        this.name = name;
    }

    @Override
    public int getCount() {
        return name.length;
    }

    @Override
    public Object getItem(int position) {
        return name[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if (convertView == null) {
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.custom_layout, null);
        }

        ImageView iv = view.findViewById(R.id.icons);
        TextView tv = view.findViewById(R.id.textName);

        iv.setImageResource(icon[position]);
        tv.setText(name[position]);

        return view;
    }
}
