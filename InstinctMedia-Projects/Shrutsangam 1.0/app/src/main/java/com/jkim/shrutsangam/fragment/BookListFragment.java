package com.jkim.shrutsangam.fragment;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.adapter.BookIssueListAdapter;
import com.jkim.shrutsangam.adapter.BookListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.BookIssueListResponse;
import com.jkim.shrutsangam.api.modal.BookListResponse;
import com.jkim.shrutsangam.utils.Constant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ProgressBar pbBookList;
    private RecyclerView rvbookList;
    private BookListAdapter bookListAdapter;
    private EditText edtSearch;
    String[] searchBy = {"Book Name","Author","Publisher","Topic"};
    String SearchBy = "name";


    private List<BookListResponse.listDataResponse> listDataResponses = new ArrayList<>();
    private List<BookListResponse.listDataResponse> listDataFilter = new ArrayList<>();

    public BookListFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_list, container, false);
        init(view);
        getBookList();
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void init(View view) {
        pbBookList = view.findViewById(R.id.pb_book_list);
        rvbookList = view.findViewById(R.id.rv_book_list);
        edtSearch = view.findViewById(R.id.et_search);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvbookList.setLayoutManager(linearLayoutManager);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(!charSequence.toString().isEmpty()) {
                            getBookListSearch(SearchBy,charSequence.toString());
                        }else{
                            getBookList();
                        }
                    }
                },500);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        Spinner spin = view.findViewById(R.id.spinner);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(Objects.requireNonNull(getActivity()),android.R.layout.simple_spinner_item,searchBy);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
    }

    private void getBookList() {
        pbBookList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<BookListResponse> bookListResponseCall = apiService.getBookList(Constant.BHANDER_ID);
        bookListResponseCall.enqueue(new Callback<BookListResponse>() {
            @Override
            public void onResponse(Call<BookListResponse> call, Response<BookListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        BookListResponse bookListResponse = response.body();
                        try {
                            List<BookListResponse.Datum> datumList = bookListResponse.getData();
                            for (BookListResponse.Datum datum : datumList) {
                                Log.e(TAG, "onResponse: " + datum.listdata);
                                listDataResponses = datum.listdata;
                                bookListAdapter = new BookListAdapter(datum.listdata, getContext());
                            }
                            rvbookList.setAdapter(bookListAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                        Log.e(TAG, "onResponse: " + bookListResponse);
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbBookList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookListResponse> call, Throwable t) {
                pbBookList.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getBookListSearch(String Name,String Value) {
        pbBookList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<BookListResponse> bookListResponseCall = apiService.getSearchBook(Name,Value,Constant.BHANDER_ID);
        bookListResponseCall.enqueue(new Callback<BookListResponse>() {
            @Override
            public void onResponse(Call<BookListResponse> call, Response<BookListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        BookListResponse bookListResponse = response.body();
                        try {
                            List<BookListResponse.Datum> datumList = bookListResponse.getData();
                            for (BookListResponse.Datum datum : datumList) {
                                Log.e(TAG, "onResponse: " + datum.listdata);
                                listDataResponses = datum.listdata;
                                bookListAdapter = new BookListAdapter(datum.listdata, getContext());
                            }
                            rvbookList.setAdapter(bookListAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                        Log.e(TAG, "onResponse: " + bookListResponse);
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbBookList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookListResponse> call, Throwable t) {
                pbBookList.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        if(i == 0){
            SearchBy = "name";
        }else if(i == 1){
            SearchBy = "AuthorId";
        }else if(i == 2){
            SearchBy = "PublisherId";
        }else if(i == 3){
            SearchBy = "BookTopic";
        }else{
            SearchBy = "name";
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
