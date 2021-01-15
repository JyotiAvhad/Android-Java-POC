package com.example.swipescreenusingviewpager;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

class ViewPagerAdapter extends PagerAdapter {

    private Context ctx;

    public ViewPagerAdapter(Context context) {

        ctx = context;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        ViewPagerModel viewPagerModel = ViewPagerModel.values()[position];
        LayoutInflater inflater = LayoutInflater.from(ctx);
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(viewPagerModel.getLayoutResId(), container, false);
        container.addView(viewGroup);

        return viewGroup;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @Override
    public int getCount() {

        return ViewPagerModel.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        ViewPagerModel viewPagerModel = ViewPagerModel.values()[position];
        return ctx.getString(viewPagerModel.getTitleResId());
    }
}
