package com.jkim.shrutsangam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.utils.interfaces.OnNavigationItemClick;

import java.util.ArrayList;

public class NavigationDrawerItemAdapter extends RecyclerView.Adapter<NavigationDrawerItemAdapter.NavigationDrawerItemHolder> {
    private ArrayList<String> arrayNavigationItemName;
    private ArrayList<Integer> arrayNavigationItemIcon;
    private Context context;
    private OnNavigationItemClick onNavigationItemClick;

    public NavigationDrawerItemAdapter(ArrayList<String> arrayNavigationItemName, ArrayList<Integer> arrayNavigationItemIcon, Context context, OnNavigationItemClick onNavigationItemClick) {
        this.arrayNavigationItemName = arrayNavigationItemName;
        this.arrayNavigationItemIcon = arrayNavigationItemIcon;
        this.context = context;
        this.onNavigationItemClick = onNavigationItemClick;
    }

    @Override
    public NavigationDrawerItemAdapter.NavigationDrawerItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NavigationDrawerItemAdapter.NavigationDrawerItemHolder(LayoutInflater.from(context).inflate(R.layout.row_navigation_item, parent, false));
    }

    @Override
    public void onBindViewHolder(final NavigationDrawerItemAdapter.NavigationDrawerItemHolder holder, final int position) {

        holder.tvNavmenuName.setText(arrayNavigationItemName.get(position));
        holder.ivNavmenuIcon.setImageResource(arrayNavigationItemIcon.get(position));
        holder.rlItems.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onNavigationItemClick.onNavigationItemClick(arrayNavigationItemName.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayNavigationItemName.size();
    }


    public class NavigationDrawerItemHolder extends RecyclerView.ViewHolder {
        private TextView tvNavmenuName;
        private ImageView ivNavmenuIcon;
        private RelativeLayout rlItems;

        public NavigationDrawerItemHolder(View itemView) {
            super(itemView);
            tvNavmenuName = itemView.findViewById(R.id.tv_navmenu_name);
            ivNavmenuIcon = itemView.findViewById(R.id.iv_navmenu_icon);
            rlItems = itemView.findViewById(R.id.rl_items);
        }

    }
}
