package com.jkim.shrutsangam.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.adapter.UsageReportAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.UsageReportListResponse;
import com.jkim.shrutsangam.utils.Constant;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UsageReportFragment extends Fragment {

    private static final String TAG = "TAG";
    private RecyclerView rvUsageReportList;
    private ProgressBar pbUsageReportList;
    private UsageReportAdapter usageReportAdapter;
    private int currentDate, currentMonth, currentYear;
    private EditText edtFrom, edtTo;
    Button btnSearch;
    private int selectedDueDate, selectedDueMonth, selectedDueYear;

    public UsageReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_usage_report, container, false);

        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDate = c.get(Calendar.DAY_OF_MONTH);
        edtFrom = view.findViewById(R.id.FromDate);
        edtTo = view.findViewById(R.id.ToDate);
        btnSearch = view.findViewById(R.id.btnSearch);
        edtTo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker("To");
            }
        });

        edtFrom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker("From");
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtFrom.getText().toString().trim().isEmpty()) {
                    edtFrom.setError("Please Enter Start Date");
                } else if (edtTo.getText().toString().trim().isEmpty()) {
                    edtTo.setError("Please Enter End Date");
                } else {
                    getUsageReportListSearch(edtFrom.getText().toString().trim() + " 00:00:00", edtTo.getText().toString().trim() + " 00:00:00");
                }
            }
        });

        init(view);
        getUsageReportList();
        return view;
    }

    private void init(View view) {
        rvUsageReportList = view.findViewById(R.id.rv_usage_report_list);
        pbUsageReportList = view.findViewById(R.id.pb_usage_report_list);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvUsageReportList.setLayoutManager(linearLayoutManager);
    }


    private void showDatePicker(final String fromWhere) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        switch (fromWhere) {
                            case "From":
                                selectedDueDate = dayOfMonth;
                                selectedDueMonth = (monthOfYear + 1);
                                selectedDueYear = year;
                                edtFrom.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                break;
                            case "To":
                                selectedDueDate = dayOfMonth;
                                selectedDueMonth = (monthOfYear + 1);
                                selectedDueYear = year;
                                edtTo.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                break;
                        }
                    }
                }, currentYear, currentMonth, currentDate);
        datePickerDialog.show();
    }


    private void getUsageReportList() {
        pbUsageReportList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UsageReportListResponse> call = apiService.getUsageReport(Constant.BHANDER_ID);
        call.enqueue(new Callback<UsageReportListResponse>() {
            @Override
            public void onResponse(Call<UsageReportListResponse> call, Response<UsageReportListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        UsageReportListResponse UsageReportListResponse = response.body();
                        List<UsageReportListResponse.Datum> datumList = UsageReportListResponse.getData();
                        for (UsageReportListResponse.Datum datum : datumList) {
                            Log.e("", "onResponse: " + datum.listdata);
                            usageReportAdapter = new UsageReportAdapter(datum.listdata, getContext());
                        }
                        rvUsageReportList.setAdapter(usageReportAdapter);
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbUsageReportList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<UsageReportListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbUsageReportList.setVisibility(View.GONE);
            }
        });

    }


    private void getUsageReportListSearch(String From, String To) {
        pbUsageReportList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<UsageReportListResponse> call = apiService.getSearchUsesReport(From, To, Constant.BHANDER_ID);
        call.enqueue(new Callback<UsageReportListResponse>() {
            @Override
            public void onResponse(Call<UsageReportListResponse> call, Response<UsageReportListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        UsageReportListResponse UsageReportListResponse = response.body();
                        List<UsageReportListResponse.Datum> datumList = UsageReportListResponse.getData();
                        for (UsageReportListResponse.Datum datum : datumList) {
                            Log.e("", "onResponse: " + datum.listdata);
                            usageReportAdapter = new UsageReportAdapter(datum.listdata, getContext());
                        }
                        rvUsageReportList.setAdapter(usageReportAdapter);
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbUsageReportList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<UsageReportListResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbUsageReportList.setVisibility(View.GONE);
            }
        });

    }
}
