package com.jkim.shrutsangam.fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.activity.BookIssueFormActivity;
import com.jkim.shrutsangam.adapter.AdminMemberListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.AdminGetMemberCodeResponse;
import com.jkim.shrutsangam.api.modal.AdminMemberListResponse;
import com.jkim.shrutsangam.api.modal.SubmitBoolEnquiryFormResponse;
import com.jkim.shrutsangam.api.modal.SubmitBoolEnquiryFormResponse;
import com.jkim.shrutsangam.utils.Constant;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookEnquiryFragment extends Fragment implements View.OnClickListener, View.OnFocusChangeListener {

    private TextView txtMemberId;
    private EditText etMemberCode, etBookName;
    private CardView cardEnquiry;
    private ProgressBar pbEnquirySubmit;

    public BookEnquiryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_enquiry, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        txtMemberId = view.findViewById(R.id.txt_member_id);
        etMemberCode = view.findViewById(R.id.et_member_code);
        etBookName = view.findViewById(R.id.et_book_name);
        cardEnquiry = view.findViewById(R.id.card_enquiry);
        pbEnquirySubmit = view.findViewById(R.id.pb_enquiry_submit);

        cardEnquiry.setOnClickListener(this);
        etMemberCode.setOnFocusChangeListener(this);

    }

    private void submitEnquiryForm() {
        pbEnquirySubmit.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<SubmitBoolEnquiryFormResponse> call = apiService.submitBookEnquiry(txtMemberId.getText().toString(), etMemberCode.getText().toString(), Constant.BHANDER_ID, etBookName.getText().toString());
        call.enqueue(new Callback<SubmitBoolEnquiryFormResponse>() {
            @Override
            public void onResponse(Call<SubmitBoolEnquiryFormResponse> call, Response<SubmitBoolEnquiryFormResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        SubmitBoolEnquiryFormResponse submitBoolEnquiryFormResponse = response.body();
                        if (submitBoolEnquiryFormResponse.getMessage().equals("success")) {
                            Toast.makeText(getContext(), "Form submitted", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                pbEnquirySubmit.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<SubmitBoolEnquiryFormResponse> call, Throwable t) {
                Log.e("", "onFailure: " + t);
                pbEnquirySubmit.setVisibility(View.GONE);
            }
        });

    }

    private void getBookList() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AdminMemberListResponse> call = apiService.getMemberList("410");
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
                                for (AdminMemberListResponse.listDataResponse listDataResponse : datum.listdata) {
                                    if (etMemberCode.getText().toString().equals(listDataResponse.getMemberCode())) {
                                        txtMemberId.setText(listDataResponse.getMberId());
                                    }

                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<AdminMemberListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_enquiry:
                if (etMemberCode.getText().toString().equals("") && etBookName.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter MemberCode and BookName", Toast.LENGTH_SHORT).show();
                } else if (etMemberCode.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter MemberCode and BookName", Toast.LENGTH_SHORT).show();
                } else if (etBookName.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter BookName", Toast.LENGTH_SHORT).show();
                } else if (txtMemberId.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Invalid member id", Toast.LENGTH_SHORT).show();
                } else
                    submitEnquiryForm();
                break;
        }
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.et_member_code:
                if (!hasFocus) {
                    getBookList();
                }
                break;
        }
    }
}
