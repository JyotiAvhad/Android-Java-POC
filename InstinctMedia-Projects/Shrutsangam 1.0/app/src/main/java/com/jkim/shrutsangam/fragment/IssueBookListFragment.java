package com.jkim.shrutsangam.fragment;


import android.content.Intent;
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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.activity.BookIssueFormActivity;
import com.jkim.shrutsangam.adapter.BookReceiveListAdapterJson;
import com.jkim.shrutsangam.adapter.IssueBookListAdapterJson;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.BookIssueListResponse;
import com.jkim.shrutsangam.api.modal.BookReceiveResponse;
import com.jkim.shrutsangam.api.modal.DashBoardResponse;
import com.jkim.shrutsangam.utils.Constant;
import com.jkim.shrutsangam.utils.Utils;
import com.jkim.shrutsangam.utils.interfaces.OnBookIssueListClick;
import com.jkim.shrutsangam.utils.interfaces.OnDialogActionClick;

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
public class IssueBookListFragment extends Fragment implements View.OnClickListener {

    private ProgressBar pbBookIssue;
    private RecyclerView rvbookIssue;
    private IssueBookListAdapterJson IssueBookListAdapter;
    private FloatingActionButton fabAdd;
    JSONArray jsonArray = new JSONArray();
    SharedPreferences prefIssueList, prefReceiveList;
    SharedPreferences.Editor editorIssueList, editorReceiveList;


    public IssueBookListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_issue_list, container, false);
        init(view);
//        getBookIssueList();
        return view;
    }

    private void init(View view) {
        pbBookIssue = view.findViewById(R.id.pb_book_issue);
        rvbookIssue = view.findViewById(R.id.rv_book_issue);
        fabAdd = view.findViewById(R.id.fab_add);

        prefIssueList = getActivity().getSharedPreferences("ISSUELIST", MODE_PRIVATE);
        editorIssueList = prefIssueList.edit();
        prefReceiveList = getActivity().getSharedPreferences("RECEIVELIST", MODE_PRIVATE);
        editorReceiveList = prefReceiveList.edit();


        try {
            jsonArray = new JSONArray(prefIssueList.getString("IssueList",""));
        } catch (JSONException e) {
            e.printStackTrace();
        }


        fabAdd.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvbookIssue.setLayoutManager(linearLayoutManager);

//        IssueBookListAdapter = new IssueBookListAdapterJson(jsonArray, getContext(), new OnBookIssueListClick() {
//            @Override
//            public void onBookIssueListClick(final String issueId, final String bhandarId) {
//                Utils utils = new Utils();
//                utils.alertDialog("Are you sure want to receive this book ?", getString(R.string.app_name), getContext(), new OnDialogActionClick() {
//                    @Override
//                    public void onDialogYes() {
//                        bookReceive(issueId, bhandarId);
//                    }
//
//                    @Override
//                    public void onDialogNo() {
//                    }
//                });
//
//                rvbookIssue.setAdapter(IssueBookListAdapter);
//            }
//        });
    }

    private void getDashBoard() {
        pbBookIssue.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);


        Call<DashBoardResponse> dashBoardResponseCall = apiService.dashBoard(Constant.BHANDER_ID);
        dashBoardResponseCall.enqueue(new Callback<DashBoardResponse>() {
            @Override
            public void onResponse(Call<DashBoardResponse> call, Response<DashBoardResponse> response) {
                Log.e(TAG, "onResponse: " + response.body().getMessage());
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {

                        pbBookIssue.setVisibility(View.GONE);
                        DashBoardResponse dashBoardResponse = response.body();
                        List<DashBoardResponse.Datum> datumList = dashBoardResponse.getData();
                        for (DashBoardResponse.Datum datum : datumList) {
                            Log.e(TAG, "onResponse: " + datum.bhandar_data);
                            JsonObject jsonObject = datum.bhandar_data.getAsJsonObject();
                            String bhanderName = String.valueOf(jsonObject.get("BhandarName"));
                            String removeQuotes = bhanderName.replace("\"", "");

                            JsonArray jsonArrayIssueList = datum.jsonArrayIssueList.getAsJsonArray();
                            JsonArray jsonArrayReceiveList = datum.jsonArrayReceiveList.getAsJsonArray();

                            if(jsonArrayIssueList.size() == 0){
                                Toast.makeText(getActivity(), "No Record Found", Toast.LENGTH_SHORT).show();

                            }

                            IssueBookListAdapter = new IssueBookListAdapterJson(jsonArrayIssueList, getContext(), new OnBookIssueListClick() {
                                @Override
                                public void onBookIssueListClick(final String issueId, final String bhandarId) {
                                    Utils utils = new Utils();
                                    utils.alertDialog("Are you sure want to receive this book ?", getString(R.string.app_name), getContext(), new OnDialogActionClick() {
                                        @Override
                                        public void onDialogYes() {
                                            bookReceive(issueId, bhandarId);
                                        }

                                        @Override
                                        public void onDialogNo() {
                                        }
                                    });

                                    rvbookIssue.setAdapter(IssueBookListAdapter);
                                }
                            });

                            Constant.DASHBOARD_BOOK_ENTRY = datum.book_entry;
                            Constant.DASHBOARD_MEMBER = datum.member;
                            Constant.DASHBOARD_RECEIVE_BOOK = datum.total_book_receive;
                            Constant.DASHBOARD_ISSUE_BOOK = datum.total_book_issue;
                            Constant.DASHBOARD_OUTSTANDING = datum.outstanding;
                            Constant.BHANDAR_NAME = removeQuotes;
                        }
                    } else
                        pbBookIssue.setVisibility(View.GONE);
                        Toast.makeText(getActivity(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbBookIssue.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<DashBoardResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbBookIssue.setVisibility(View.GONE);
            }
        });
    }

    private void getBookIssueList() {
        pbBookIssue.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<BookIssueListResponse> call = apiService.getBookIssueList(Constant.BHANDER_ID);
        call.enqueue(new Callback<BookIssueListResponse>() {
            @Override
            public void onResponse(Call<BookIssueListResponse> call, Response<BookIssueListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        BookIssueListResponse bookIssueListResponse = response.body();
                        try {
                            List<BookIssueListResponse.Datum> datumList = bookIssueListResponse.getData();
                            for (BookIssueListResponse.Datum datum : datumList) {
                                Log.e(TAG, "onResponse: " + datum.listdata);


//                        for (BookIssueListResponse.listDataResponse listDataResponse : datum.listdata) {
//                            Log.e(TAG, "onResponse: " + listDataResponse.getName());
//                        }
//                                IssueBookListAdapter = new IssueBookListAdapterJson(jsonArray, getContext(), new OnBookIssueListClick() {
//                                    @Override
//                                    public void onBookIssueListClick(final String issueId, final String bhandarId) {
//                                        Utils utils = new Utils();
//                                        utils.alertDialog("Are you sure want to receive this book ?", getString(R.string.app_name), getContext(), new OnDialogActionClick() {
//                                            @Override
//                                            public void onDialogYes() {
//                                                bookReceive(issueId, bhandarId);
//                                            }
//
//                                            @Override
//                                            public void onDialogNo() {
//                                            }
//                                        });
//                                    }
//                                });
                            }
//                            rvbookIssue.setAdapter(IssueBookListAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbBookIssue.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookIssueListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbBookIssue.setVisibility(View.GONE);
            }
        });
    }

    private void bookReceive(String issueId, String bhandarId) {
        pbBookIssue.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<BookReceiveResponse> call = apiService.bookReceive(issueId, Constant.BHANDER_ID, "1");
        call.enqueue(new Callback<BookReceiveResponse>() {
            @Override
            public void onResponse(Call<BookReceiveResponse> call, Response<BookReceiveResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        getDashBoard();
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbBookIssue.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookReceiveResponse> call, Throwable t) {
                pbBookIssue.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_add:
                startActivity(new Intent(getContext(), BookIssueFormActivity.class));
                break;

        }
    }
}
