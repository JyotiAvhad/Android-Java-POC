package com.example.eatfood;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.eatfood.navigationfragment.AboutNavigationFragment;
import com.example.eatfood.navigationfragment.LogoutNavigationFragment;
import com.example.eatfood.navigationfragment.ProfileNavigationFragment;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private static final String TAG = "MainActivity";
  //  private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);


        //set navigation item selected listener
        navigationView.setNavigationItemSelectedListener(this);


        //add menu icon to main activity to display slider navigation screen
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        //after opening app default fragment immediately
//        if (savedInstanceState==null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
//                    new ProfileNavigationFragment()).commit();
//            navigationView.setCheckedItem(R.id.profile_item);
//        }


//        //adding tabs

      //  sectionsPagerAdapter=new SectionsPagerAdapter(getSupportFragmentManager());

        //set up view pager with sections adapter
        viewPager=findViewById(R.id.view_pager);
       // setViewPager(viewPager);

        TabLayout tabLayout=findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
//        TabLayout tabLayout = findViewById(R.id.tab_layout);
//        tabLayout.addTab(tabLayout.newTab().setText("Indian"));
//        tabLayout.addTab(tabLayout.newTab().setText("Chinese"));
//        tabLayout.addTab(tabLayout.newTab().setText("Thai"));
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = findViewById(R.id.view_pager);

//        viewPager.setAdapter(pagerAdapter);
//        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }


    @Override
    public boolean onOptionsItemSelected(@Nullable MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {

            case R.id.profile_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ProfileNavigationFragment()).commit();  //used to place our fragment in fragment container frame_layout
                break;

            case R.id.about_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new AboutNavigationFragment()).commit();
                break;

            case R.id.logout_item:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LogoutNavigationFragment()).commit();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

//    //after click on menu icon slider bar will be display
//    @Override
//    public void onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }

//    private void setViewPager(ViewPager viewPager){
//
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager());
//        sectionsPagerAdapter.addFragment(new IndianFoodTabFragment(),"Indian Food");
//        sectionsPagerAdapter.addFragment(new ChineseFoodTabFragment(),"Chinese Food");
//        sectionsPagerAdapter.addFragment(new ThaiFoodTabFragment(),"Thai Food");
//    }
}
