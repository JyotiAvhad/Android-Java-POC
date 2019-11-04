package com.jkim.shrutsangam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.adapter.ExpandableListViewAdapter;
import com.jkim.shrutsangam.fragment.AdminBookIssueFormFragment;
import com.jkim.shrutsangam.fragment.AdminMemberListFragment;
import com.jkim.shrutsangam.fragment.BookEntryFragment;
import com.jkim.shrutsangam.fragment.BookIssueListFragment;
import com.jkim.shrutsangam.fragment.BookListFragment;
import com.jkim.shrutsangam.fragment.BookReceiveFragment;
import com.jkim.shrutsangam.fragment.AdminDashboardFragment;
import com.jkim.shrutsangam.fragment.ImageListFragment;
import com.jkim.shrutsangam.utils.Constant;
import com.jkim.shrutsangam.utils.ExpandableAdminListDataPump;
import com.jkim.shrutsangam.utils.ExpandableListDataPump;
import com.jkim.shrutsangam.utils.SessionManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "TAG";
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ImageView drawerToggle;
    private CoordinatorLayout clContent;
    private TextView tvTitle, txtBhandarName;
    private ImageView imgCloseDrawer;
    private RecyclerView rvDrawer;
    private ProgressBar pbDashboard;
    private ExpandableListView elDrawer;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private SessionManager sessionManager;
    private String adminName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        sessionManager = new SessionManager(this);
        getSessionLoginData();
        init();
        configureNavigationDrawer();
    }

    private void init() {
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = findViewById(R.id.drawer_toggle);
        tvTitle = findViewById(R.id.tv_title);
        txtBhandarName = findViewById(R.id.txt_bhandar_name);
        imgCloseDrawer = findViewById(R.id.img_close_drawer);
        rvDrawer = findViewById(R.id.rv_drawer);
        elDrawer = findViewById(R.id.el_drawer);
        navView = findViewById(R.id.nav_view);
        clContent = findViewById(R.id.cl_content);
        pbDashboard = findViewById(R.id.pb_dashboard);
        pbDashboard.setVisibility(View.GONE);

        drawerToggle.setOnClickListener(this);
        imgCloseDrawer.setOnClickListener(this);

        loadFragment(new AdminDashboardFragment());
    }

    private void getSessionLoginData() {
        Log.e(TAG, "getSessionLoginData: " + sessionManager.isAdminLoggedIn());
        if (!sessionManager.isAdminLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }

        HashMap<String, String> admin = sessionManager.getAdminDetails();
        Constant.ADMIN_ID = admin.get(SessionManager.KEY_ADMIN_ID);
        adminName = admin.get(SessionManager.KEY_ADMIN_USERNAME);
    }


    private void configureNavigationDrawer() {
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                clContent.setTranslationX(slideX);
            }
        });

        expandableListDetail = ExpandableAdminListDataPump.getData();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        Collections.reverse(expandableListTitle);

        ExpandableListViewAdapter expandableListAdapter = new ExpandableListViewAdapter(this, expandableListTitle, expandableListDetail);
        elDrawer.setAdapter(expandableListAdapter);

        elDrawer.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (expandableListTitle.get(groupPosition).equals("Dashboard")) {
                    changeToolbarName("Dashboard");
                    loadFragment(new AdminDashboardFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
        elDrawer.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                if (expandableListTitle.get(groupPosition).equals("Dashboard")) {
                    changeToolbarName("Dashboard");
                    loadFragment(new AdminDashboardFragment());
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
            }
        });
        elDrawer.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String childItemName = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                switch (childItemName) {
                    case "Book Issues Form":
                        changeToolbarName("Book Issues Form");
                        loadFragment(new AdminBookIssueFormFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Book Receive Form":
                        changeToolbarName("Book Receive Form");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Member List":
                        changeToolbarName("Member List");
                        loadFragment(new AdminMemberListFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Add Member":
                        changeToolbarName("Add Member");
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
                return false;
            }
        });
    }

    private void changeToolbarName(String title) {
        tvTitle.setText(title);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment, "HomeFragment");
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.drawer_toggle:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.img_close_drawer:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
    }
}
