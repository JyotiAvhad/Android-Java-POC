package com.jkim.shrutsangam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.adapter.ExpandableListViewAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.DashBoardResponse;
import com.jkim.shrutsangam.fragment.BookEnquiryFragment;
import com.jkim.shrutsangam.fragment.BookEntryFragment;
import com.jkim.shrutsangam.fragment.BookIssueListFragment;
import com.jkim.shrutsangam.fragment.BookListFragment;
import com.jkim.shrutsangam.fragment.BookReceiveFragment;
import com.jkim.shrutsangam.fragment.DashboardFragment;
import com.jkim.shrutsangam.fragment.ImageListFragment;
import com.jkim.shrutsangam.fragment.OutstandingListFragment;
import com.jkim.shrutsangam.fragment.UsageReportFragment;
import com.jkim.shrutsangam.utils.Constant;
import com.jkim.shrutsangam.utils.ExpandableListDataPump;
import com.jkim.shrutsangam.utils.Utils;
import com.jkim.shrutsangam.utils.interfaces.OnDialogActionClick;
import com.jkim.shrutsangam.utils.interfaces.OnNavigationItemClick;
import com.jkim.shrutsangam.utils.SessionManager;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardActivity extends AppCompatActivity implements View.OnClickListener, OnNavigationItemClick {


    private static final String TAG = "TAg";
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ImageView drawerToggle;
    private CoordinatorLayout clContent;
    private TextView tvTitle, txtBhandarName;
    private ImageView imgCloseDrawer, imgLogout;

    private ProgressBar pbDashboard;
    private RecyclerView rvDrawer;
    private ExpandableListView elDrawer;
    private ArrayList<String> arrayNavigationItemName = new ArrayList<>();
    private ArrayList<Integer> arrayNavigationItemIcon = new ArrayList<>();
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    private SessionManager sessionManager;
    private String id, username, fullname, email, mobile;
    SharedPreferences prefIssueList, prefReceiveList;      // to Store private primitive data in key-value pairs
    SharedPreferences.Editor editorIssueList, editorReceiveList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        sessionManager = new SessionManager(this);


        Log.d(TAG, "onCreate: uuuuuuuuuuuuuuu rrrrrrrrrrrrrrr hereeeeeeeeeeeeeeeeeeee");

        getSessionLoginData();      //get user details via shared preference
        init();
        configureNavigationDrawer();
    }

    /*
    type casting
     */
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
        imgLogout = findViewById(R.id.img_logout);

        prefIssueList = getSharedPreferences("ISSUELIST", MODE_PRIVATE);
        editorIssueList = prefIssueList.edit();
        prefReceiveList = getSharedPreferences("RECEIVELIST", MODE_PRIVATE);
        editorReceiveList = prefReceiveList.edit();


        drawerToggle.setOnClickListener(this);
        imgCloseDrawer.setOnClickListener(this);
        imgLogout.setOnClickListener(this);

    }

    private void getSessionLoginData() {
        Log.e(TAG, "getSessionLoginData: " + sessionManager.isLoggedIn());
//        if (!sessionManager.isLoggedIn()) {
//            if (sessionManager.isAdminLoggedIn()) {
//                startActivity(new Intent(this, AdminDashboardActivity.class));
//                finish();
//            } else {
//                startActivity(new Intent(this, LoginActivity.class));
//                finish();
//            }
//        } else {
        HashMap<String, String> user = sessionManager.getUserDetails();
        Constant.USER_ID = user.get(SessionManager.KEY_ID);
        Constant.BHANDER_ID = user.get(SessionManager.KEY_BHANDER_ID);
        username = user.get(SessionManager.KEY_USERNAME);
        fullname = user.get(SessionManager.KEY_FULLNAME);
        email = user.get(SessionManager.KEY_EMAIL);
        mobile = user.get(SessionManager.KEY_MOBILE);

        getDashBoard();     //API Calling
//        }
    }


    private void configureNavigationDrawer() {

        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                //Called when a drawer's position changes.
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                clContent.setTranslationX(slideX);
            }
        });
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        rvDrawer.setLayoutManager(linearLayoutManager);
//
//        arrayNavigationItemName.add("Dashboard");
//        arrayNavigationItemName.add("Book Issues Form");
//        arrayNavigationItemName.add("Book Receive Form");
//        arrayNavigationItemName.add("Book Reminder/ Pending");
//        arrayNavigationItemName.add("Book Enquiry Form");
//        arrayNavigationItemName.add("Book Entry");
//
//        arrayNavigationItemIcon.add(R.drawable.ic_home_black_24dp);
//        arrayNavigationItemIcon.add(R.drawable.ic_home_black_24dp);
//        arrayNavigationItemIcon.add(R.drawable.ic_home_black_24dp);
//        arrayNavigationItemIcon.add(R.drawable.ic_home_black_24dp);
//        arrayNavigationItemIcon.add(R.drawable.ic_home_black_24dp);
//        arrayNavigationItemIcon.add(R.drawable.ic_home_black_24dp);
//
//        NavigationDrawerItemAdapter navigationDrawerItemAdapter = new NavigationDrawerItemAdapter(arrayNavigationItemName, arrayNavigationItemIcon, this, this);
//        rvDrawer.setAdapter(navigationDrawerItemAdapter);


        expandableListDetail = ExpandableListDataPump.getData();        //add slider menu items
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());
        Collections.reverse(expandableListTitle);

        ExpandableListViewAdapter expandableListAdapter = new ExpandableListViewAdapter(this, expandableListTitle, expandableListDetail);
        elDrawer.setAdapter(expandableListAdapter);

        elDrawer.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                String title = expandableListTitle.get(groupPosition);
                switch (title) {
                    case "Dashboard":
                        changeToolbarName("Dashboard");
                        loadFragment(new DashboardFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Usage Report":
                        changeToolbarName("Usage Report");
                        loadFragment(new UsageReportFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
        elDrawer.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                String title = expandableListTitle.get(groupPosition);
                switch (title) {
                    case "Dashboard":
                        changeToolbarName("Dashboard");
                        loadFragment(new DashboardFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Usage Report":
                        changeToolbarName("Usage Report");
                        loadFragment(new UsageReportFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }
            }
        });
        elDrawer.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                String childItemName = expandableListDetail.get(expandableListTitle.get(groupPosition)).get(childPosition);
                switch (childItemName) {
                    case "Book Issues":
                        changeToolbarName("Book Issue list");
                        loadFragment(new BookIssueListFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Image Upload":
                        changeToolbarName("Image Upload");
                        loadFragment(new BookEntryFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Book Receive":
                        changeToolbarName("Book Receive list");
                        loadFragment(new BookReceiveFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case "Book List":
                        changeToolbarName("Book List");
                        loadFragment(new BookListFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
//                    case "Book Pending":
//                        changeToolbarName("Book Pending List");
//                        loadFragment(new OutstandingListFragment());
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
                    case "Image List":
                        changeToolbarName("Image List");
                        loadFragment(new ImageListFragment());
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
//                    case "Book Enquiry Form":
//                        changeToolbarName("Book Enquiry Form");
//                        loadFragment(new BookEnquiryFragment());
//                        drawerLayout.closeDrawer(GravityCompat.START);
//                        break;
                }
                return false;
            }
        });


    }


    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment, "HomeFragment");
//        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void changeToolbarName(String title) {
        tvTitle.setText(title);
    }

    private void getDashBoard() {
        pbDashboard = findViewById(R.id.pb_dashboard);
        //loading indicator ON
        pbDashboard.setVisibility(View.VISIBLE);

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<DashBoardResponse> dashBoardResponseCall = apiService.dashBoard(Constant.BHANDER_ID);
        dashBoardResponseCall.enqueue(new Callback<DashBoardResponse>() {
            @Override
            public void onResponse(Call<DashBoardResponse> call, Response<DashBoardResponse> response) {
                Log.e(TAG, "onResponse: " + response.body().getMessage());


                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {

                        DashBoardResponse dashBoardResponse = response.body();

                        List<DashBoardResponse.Datum> datumList = dashBoardResponse.getData();

                        for (DashBoardResponse.Datum datum : datumList) {

                            Log.e(TAG, "onResponse: " + datum.bhandar_data);

                            JsonObject jsonObject = datum.bhandar_data.getAsJsonObject();
                            String bhanderName = String.valueOf(jsonObject.get("BhandarName"));
                            String removeQuotes = bhanderName.replace("\"", "");

                            JsonArray jsonArrayIssueList = datum.jsonArrayIssueList.getAsJsonArray();
                            JsonArray jsonArrayReceiveList = datum.jsonArrayReceiveList.getAsJsonArray();

                            String jsonArray = jsonArrayIssueList.iterator().toString();

                            editorIssueList.putString("IssueList", jsonArrayIssueList.toString());
                            editorIssueList.apply();
                            editorReceiveList.putString("ReceiveList", jsonArrayReceiveList.toString());
                            editorReceiveList.apply();
//                            txtBhandarName.setText(removeQuotes);
                            Constant.DASHBOARD_BOOK_ENTRY = datum.book_entry;
                            Constant.DASHBOARD_MEMBER = datum.member;
                            Constant.DASHBOARD_RECEIVE_BOOK = datum.total_book_receive;
                            Constant.DASHBOARD_ISSUE_BOOK = datum.total_book_issue;
                            Constant.DASHBOARD_OUTSTANDING = datum.outstanding;
                            Constant.BHANDAR_NAME = removeQuotes;
                        }
                        loadFragment(new DashboardFragment());
                    } else
                        Toast.makeText(DashboardActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(DashboardActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                //loading indicator off
                pbDashboard.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DashBoardResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbDashboard.setVisibility(View.GONE);
            }
        });
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
            case R.id.img_logout:
                Utils utils = new Utils();
                utils.alertDialog("Are you sure want to logout ?", getString(R.string.app_name), this, new OnDialogActionClick() {
                    @Override
                    public void onDialogYes() {
                        sessionManager.logoutUser();
                        startActivity(new Intent(DashboardActivity.this, LoginActivity.class));
                        finish();
                    }

                    @Override
                    public void onDialogNo() {
                    }
                });
                break;
        }
    }

    @Override
    public void onNavigationItemClick(String itemName) {
        switch (itemName) {
            case "Dashboard":
                loadFragment(new DashboardFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case "Book Entry":
                loadFragment(new BookEntryFragment());
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

        }
    }
}
