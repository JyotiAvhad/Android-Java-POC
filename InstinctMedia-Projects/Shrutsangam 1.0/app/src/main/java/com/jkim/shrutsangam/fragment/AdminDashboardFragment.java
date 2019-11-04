package com.jkim.shrutsangam.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.activity.AdminDashboardActivity;
import com.jkim.shrutsangam.activity.DashboardActivity;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.AdminDashboardResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminDashboardFragment extends Fragment {
    private static final String TAG = "TAG";
    private TextView txtAdminBooks, txtAdminMember, txtAdminBhander, txtAdminBookCategory;
    private LinearLayout llAdminDashboard;
    private ProgressBar pbAdminDashboard;

    public AdminDashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_dashboard, container, false);
        init(view);
        getAdminDashboard();
        return view;
    }

    private void init(View view) {
        txtAdminBooks = view.findViewById(R.id.txt_admin_books);
        txtAdminMember = view.findViewById(R.id.txt_admin_member);
        txtAdminBhander = view.findViewById(R.id.txt_admin_bhander);
        txtAdminBookCategory = view.findViewById(R.id.txt_admin_book_category);
        llAdminDashboard = view.findViewById(R.id.ll_admin_dashboard);
        pbAdminDashboard = view.findViewById(R.id.pb_admin_dashboard);
    }

    private void getAdminDashboard() {
        pbAdminDashboard.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<AdminDashboardResponse> call = apiService.getAdminDashBoard("1");
        call.enqueue(new Callback<AdminDashboardResponse>() {
            @Override
            public void onResponse(Call<AdminDashboardResponse> call, Response<AdminDashboardResponse> response) {
                Log.e(TAG, "onResponse: " + response.body().getMessage());

                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        AdminDashboardResponse adminDashboardResponse = response.body();
                        List<AdminDashboardResponse.Datum> datumList = adminDashboardResponse.getData();
                        for (AdminDashboardResponse.Datum datum : datumList) {
                            JsonObject jsonObject = datum.web_admin.getAsJsonObject();
                            String totalUser = String.valueOf(jsonObject.get("totaluser"));
                            String totalBook = String.valueOf(jsonObject.get("totalbook"));
//                            String removeQuotes = bhanderName.replace("\"", "");

                            JsonObject jsonObject1 = datum.bhnadar_master.getAsJsonObject();
                            String totalbhandarMaster = String.valueOf(jsonObject1.get("totalbhandar_master"));
                            String totalbookCategory = String.valueOf(jsonObject1.get("totalbook_category"));

                            txtAdminMember.setText(totalUser);
                            txtAdminBooks.setText(totalBook);
                            txtAdminBhander.setText(totalbhandarMaster);
                            txtAdminBookCategory.setText(totalbookCategory);
                        }
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbAdminDashboard.setVisibility(View.GONE);
                llAdminDashboard.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<AdminDashboardResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbAdminDashboard.setVisibility(View.GONE);
            }
        });
    }

}
