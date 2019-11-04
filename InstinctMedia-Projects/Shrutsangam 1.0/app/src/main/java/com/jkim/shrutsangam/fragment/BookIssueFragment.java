package com.jkim.shrutsangam.fragment;


import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.jkim.shrutsangam.R;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookIssueFragment extends Fragment implements View.OnClickListener {

    private CardView cardDueDate, cardIssueDate, cardReceiveDate;
    private TextView txtDueDate, txtIssueDate, txtReceiveDate;

    public BookIssueFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_issue, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        cardDueDate = view.findViewById(R.id.card_due_date);
        cardIssueDate = view.findViewById(R.id.card_issue_date);
        cardReceiveDate = view.findViewById(R.id.card_receive_date);
        txtDueDate = view.findViewById(R.id.txt_due_date);
        txtIssueDate = view.findViewById(R.id.txt_issue_date);
        txtReceiveDate = view.findViewById(R.id.txt_receive_date);

        cardDueDate.setOnClickListener(this);
        cardIssueDate.setOnClickListener(this);
        cardReceiveDate.setOnClickListener(this);
    }

    private void showDatePicker(final String fromWhere) {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        switch (fromWhere) {
                            case "DueDate":
                                txtDueDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                break;
                            case "IssueDate":
                                txtIssueDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                break;
                            case "ReceiveDate":
                                txtReceiveDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                break;
                        }
//                        txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_due_date:
                showDatePicker("DueDate");
                break;
            case R.id.card_issue_date:
                showDatePicker("IssueDate");
                break;
            case R.id.card_receive_date:
                showDatePicker("ReceiveDate");
                break;
        }
    }
}
