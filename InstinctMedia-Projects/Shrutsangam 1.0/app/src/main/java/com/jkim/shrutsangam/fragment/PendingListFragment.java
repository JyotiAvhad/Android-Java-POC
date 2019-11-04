package com.jkim.shrutsangam.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.adapter.OutstandingListAdapter;
import com.jkim.shrutsangam.adapter.PendingListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.OutstandingListResponse;
import com.jkim.shrutsangam.api.modal.PendingListResponse;
import com.jkim.shrutsangam.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PendingListFragment extends Fragment {

    private static final String TAG = "TAG";
    private RecyclerView rvPendingList;
    private ProgressBar pbPendingList;
    private PendingListAdapter pendingListAdapter;

    public PendingListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending_list, container, false);
        init(view);
        getPendingList();
        return view;
    }

    private void init(View view) {
        rvPendingList = view.findViewById(R.id.rv_pending_list);
        pbPendingList = view.findViewById(R.id.pb_pending_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvPendingList.setLayoutManager(linearLayoutManager);
    }

    private void getPendingList() {
        pbPendingList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<PendingListResponse> call = apiService.getPending(Constant.BHANDER_ID);
        call.enqueue(new Callback<PendingListResponse>() {
            @Override
            public void onResponse(Call<PendingListResponse> call, Response<PendingListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        PendingListResponse outstandingListResponse = response.body();
                        List<PendingListResponse.Datum> datumList = outstandingListResponse.getData();
                        for (PendingListResponse.Datum datum : datumList) {
                            Log.e("", "onResponse: " + datum.total_book);
                            pendingListAdapter = new PendingListAdapter(datum.total_book, getContext());
                        }
                        rvPendingList.setAdapter(pendingListAdapter);
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbPendingList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<PendingListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbPendingList.setVisibility(View.GONE);
            }
        });

    }

}
