package com.jkim.shrutsangam.fragment;


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

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.adapter.BookListAdapter;
import com.jkim.shrutsangam.adapter.OutstandingListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.OutstandingListResponse;
import com.jkim.shrutsangam.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutstandingListFragment extends Fragment {

    private static final String TAG = "TAG";
    private RecyclerView rvOutStandingList;
    private ProgressBar pbOutStandingList;
    private OutstandingListAdapter outstandingListAdapter;

    public OutstandingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_outstanding_list, container, false);
        init(view);
        getOutstandingList();
        return view;
    }

    private void init(View view) {
        rvOutStandingList = view.findViewById(R.id.rv_outstanding_list);
        pbOutStandingList = view.findViewById(R.id.pb_outstanding_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvOutStandingList.setLayoutManager(linearLayoutManager);
    }

    private void getOutstandingList() {
        pbOutStandingList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<OutstandingListResponse> call = apiService.getOutstanding(Constant.BHANDER_ID);
        call.enqueue(new Callback<OutstandingListResponse>() {
            @Override
            public void onResponse(Call<OutstandingListResponse> call, Response<OutstandingListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        OutstandingListResponse outstandingListResponse = response.body();
                        List<OutstandingListResponse.Datum> datumList = outstandingListResponse.getData();
                        for (OutstandingListResponse.Datum datum : datumList) {
                            Log.e("", "onResponse: " + datum.total_book);
                            outstandingListAdapter = new OutstandingListAdapter(datum.total_book, getContext());
                        }
                        rvOutStandingList.setAdapter(outstandingListAdapter);
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbOutStandingList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<OutstandingListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbOutStandingList.setVisibility(View.GONE);
            }
        });

    }

}
