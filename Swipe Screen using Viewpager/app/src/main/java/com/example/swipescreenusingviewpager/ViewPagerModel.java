package com.example.swipescreenusingviewpager;

public enum ViewPagerModel {

    ONE(R.string.one, R.layout.first_layout),

    TWO(R.string.two, R.layout.second_layout),

    THREE(R.string.three, R.layout.third_layout);

    private int titleResId;
    private int layoutResId;

    ViewPagerModel(int title, int layout) {

        titleResId = title;
        layoutResId = layout;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getLayoutResId() {
        return layoutResId;
    }
}
