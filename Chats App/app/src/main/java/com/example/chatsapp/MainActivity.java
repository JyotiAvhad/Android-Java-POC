package com.example.chatsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private SectionsPageAdapter sectionsPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Starting");

        sectionsPageAdapter=new SectionsPageAdapter(getSupportFragmentManager());

        // Set ViewPager with the sections adapter.
        ViewPager viewPager=findViewById(R.id.viewPager);
        setViewPager(viewPager);

        TabLayout tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void setViewPager(ViewPager viewPager) {

        SectionsPageAdapter adapter=new SectionsPageAdapter(getSupportFragmentManager());
        adapter.addFragment(new ChatsFragment(),"Chats");
        adapter.addFragment(new StatusFragment(),"Status");
        adapter.addFragment(new CallsFragment(),"Calls");
        viewPager.setAdapter(adapter);

    }
}
