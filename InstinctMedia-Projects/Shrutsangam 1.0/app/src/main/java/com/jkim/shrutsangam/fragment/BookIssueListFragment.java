package com.jkim.shrutsangam.fragment;


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
import com.jkim.shrutsangam.activity.BookIssueFormActivity;
import com.jkim.shrutsangam.adapter.BookIssueListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.BookIssueListResponse;
import com.jkim.shrutsangam.api.modal.BookReceiveResponse;
import com.jkim.shrutsangam.utils.Constant;
import com.jkim.shrutsangam.utils.Utils;
import com.jkim.shrutsangam.utils.interfaces.OnBookIssueListClick;
import com.jkim.shrutsangam.utils.interfaces.OnDialogActionClick;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookIssueListFragment extends Fragment implements View.OnClickListener {

    private ProgressBar pbBookIssue;
    private RecyclerView rvbookIssue;
    private BookIssueListAdapter bookIssueListAdapter;
    private FloatingActionButton fabAdd;

    public BookIssueListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onStart() {
        super.onStart();

        getBookIssueList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_issue_list, container, false);
        init(view);
        getBookIssueList();
        return view;
    }

    private void init(View view) {
        pbBookIssue = view.findViewById(R.id.pb_book_issue);
        rvbookIssue = view.findViewById(R.id.rv_book_issue);
        fabAdd = view.findViewById(R.id.fab_add);

        fabAdd.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvbookIssue.setLayoutManager(linearLayoutManager);
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
                                bookIssueListAdapter = new BookIssueListAdapter(datum.listdata, getContext(), new OnBookIssueListClick() {
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
                                    }
                                });
                            }
                            rvbookIssue.setAdapter(bookIssueListAdapter);
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
                        getBookIssueList();
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
