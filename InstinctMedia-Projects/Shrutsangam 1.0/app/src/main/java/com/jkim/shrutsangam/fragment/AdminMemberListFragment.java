package com.jkim.shrutsangam.fragment;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.activity.AddMemberFormActivity;
import com.jkim.shrutsangam.activity.BookIssueFormActivity;
import com.jkim.shrutsangam.adapter.AdminMemberListAdapter;
import com.jkim.shrutsangam.adapter.BookListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.AdminMemberListResponse;
import com.jkim.shrutsangam.api.modal.BookListResponse;
import com.jkim.shrutsangam.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminMemberListFragment extends Fragment {

    private RecyclerView rvMemberList;
    private ProgressBar pbMemberList;
    private AdminMemberListAdapter adminMemberListAdapter;
    FloatingActionButton f_add;


    public AdminMemberListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_member_list, container, false);
        init(view);
        getBookList();
        return view;
    }


    @Override
    public void onStart() {
        getBookList();
        super.onStart();
    }

    private void init(View view) {
        pbMemberList = view.findViewById(R.id.pb_member_list);
        f_add = view.findViewById(R.id.fab_add);
        f_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), AddMemberFormActivity.class));

            }
        });
        rvMemberList = view.findViewById(R.id.rv_member_list);

        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvMemberList.setLayoutManager(linearLayoutManager);
    }

    private void getBookList() {
        pbMemberList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AdminMemberListResponse> call = apiService.getMemberList(Constant.BHANDER_ID);
        call.enqueue(new Callback<AdminMemberListResponse>() {
            @Override
            public void onResponse(Call<AdminMemberListResponse> call, Response<AdminMemberListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        AdminMemberListResponse adminMemberListResponse = response.body();
                        try {
                            List<AdminMemberListResponse.Datum> datumList = adminMemberListResponse.getData();
                            for (AdminMemberListResponse.Datum datum : datumList) {
                                Log.e(TAG, "onResponse: " + datum.listdata);
                                adminMemberListAdapter = new AdminMemberListAdapter(datum.listdata, getContext());
                            }
                            rvMemberList.setAdapter(adminMemberListAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbMemberList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AdminMemberListResponse> call, Throwable t) {
                pbMemberList.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
