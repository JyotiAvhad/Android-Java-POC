package com.jkim.shrutsangam.fragment;


import android.content.SharedPreferences;
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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.activity.DashboardActivity;
import com.jkim.shrutsangam.adapter.BookReceiveListAdapter;
import com.jkim.shrutsangam.adapter.BookReceiveListAdapterJson;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.BookReceiveListResponse;
import com.jkim.shrutsangam.api.modal.DashBoardResponse;
import com.jkim.shrutsangam.utils.Constant;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;
import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookReceiveFragment extends Fragment {
    private ProgressBar pbBookReceiveList;
    private RecyclerView rvbookReceiveList;
    private BookReceiveListAdapterJson bookReceiveListAdapter;
    JSONArray jsonArray = new JSONArray();
    SharedPreferences prefIssueList, prefReceiveList;
    SharedPreferences.Editor editorIssueList, editorReceiveList;

    public BookReceiveFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_receive, container, false);
        init(view);
        getDashBoard();
        return view;
    }

    private void init(View view) {
        rvbookReceiveList = view.findViewById(R.id.rv_book_receive_list);
        pbBookReceiveList = view.findViewById(R.id.pb_book_receive_list);

//        prefIssueList = getActivity().getSharedPreferences("ISSUELIST", MODE_PRIVATE);
//        editorIssueList = prefIssueList.edit();
//        prefReceiveList = getActivity().getSharedPreferences("RECEIVELIST", MODE_PRIVATE);
//        editorReceiveList = prefReceiveList.edit();
//
//        try {
//            jsonArray = new JSONArray(prefIssueList.getString("ReceiveList", ""));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvbookReceiveList.setLayoutManager(linearLayoutManager);

//        bookReceiveListAdapter = new BookReceiveListAdapterJson(jsonArray, getContext());
//        rvbookReceiveList.setAdapter(bookReceiveListAdapter);

    }


    private void getDashBoard() {
        pbBookReceiveList.setVisibility(View.VISIBLE);
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
                            if(jsonArrayReceiveList.size() == 0){
                                Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_SHORT).show();

                            }

                            bookReceiveListAdapter = new BookReceiveListAdapterJson(jsonArrayReceiveList, getContext());
                            rvbookReceiveList.setAdapter(bookReceiveListAdapter);
                            pbBookReceiveList.setVisibility(View.GONE);

                            Constant.DASHBOARD_BOOK_ENTRY = datum.book_entry;
                            Constant.DASHBOARD_MEMBER = datum.member;
                            Constant.DASHBOARD_RECEIVE_BOOK = datum.total_book_receive;
                            Constant.DASHBOARD_ISSUE_BOOK = datum.total_book_issue;
                            Constant.DASHBOARD_OUTSTANDING = datum.outstanding;
                            Constant.BHANDAR_NAME = removeQuotes;
                        }
                    } else
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbBookReceiveList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DashBoardResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbBookReceiveList.setVisibility(View.GONE);
            }
        });
    }

    private void getBookReceiveList() {
        pbBookReceiveList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<BookReceiveListResponse> call = apiService.getBookReceiveList(Constant.BHANDER_ID);
        call.enqueue(new Callback<BookReceiveListResponse>() {
            @Override
            public void onResponse(Call<BookReceiveListResponse> call, Response<BookReceiveListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        BookReceiveListResponse bookReceiveListResponse = response.body();
                        Log.e(TAG, "onResponse: " + bookReceiveListResponse);
                        try {
                            List<BookReceiveListResponse.Datum> datumList = bookReceiveListResponse.getData();
                            for (BookReceiveListResponse.Datum datum : datumList) {
                                Log.e(TAG, "onResponse: " + datum.listdata);


//                        for (BookReceiveListResponse.listDataResponse listDataResponse : datum.listdata) {
//                            Log.e(TAG, "onResponse: " + listDataResponse.getName());
//                        }
//                                bookReceiveListAdapter = new BookReceiveListAdapter(datum.listdata, getContext());
                            }
//                            rvbookReceiveList.setAdapter(bookReceiveListAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    try {
                        Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                pbBookReceiveList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookReceiveListResponse> call, Throwable t) {
                pbBookReceiveList.setVisibility(View.GONE);
                Toast.makeText(getContext(), t.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
