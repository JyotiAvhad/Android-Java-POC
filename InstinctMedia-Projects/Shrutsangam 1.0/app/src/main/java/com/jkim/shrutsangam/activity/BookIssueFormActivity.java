package com.jkim.shrutsangam.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.AdminGetBookNoResponse;
import com.jkim.shrutsangam.api.modal.AdminGetMemberCodeResponse;
import com.jkim.shrutsangam.api.modal.BookIssueFormResponse;
import com.jkim.shrutsangam.api.modal.MemberCode;
import com.jkim.shrutsangam.api.modal.MemberName;
import com.jkim.shrutsangam.utils.Constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookIssueFormActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener, View.OnFocusChangeListener {

    private static final String TAG = "TAG";
    private CardView cardDueDate, cardIssueDate, cardReceiveDate, cardSubmitForm, cardCancel;
    private TextView txtIssueDate, txtReceiveDate, txtStockStatus, txtMemberMobile;
    private ImageView imgBack;
    private EditText etDueDate;
    private ProgressBar pbFormSubmit;
    private EditText etBookNo, etBookName, etCondition, etRemark;
    private AutoCompleteTextView atMemberCode, atMemberName;
    private int selectedDueDate, selectedDueMonth, selectedDueYear;
    private int currentDate, currentMonth, currentYear;
    private String bookNoStatus = "";
    private ArrayList<String> arrayListMemberCode = new ArrayList<>();
    private ArrayList<String> arrayListMemberName = new ArrayList<>();
    private ArrayList<String> arrayListMemberMobile = new ArrayList<>();
    private ArrayList<String> arrayListMemberID = new ArrayList<>();
    private ArrayList<MemberCode> memberCodes = new ArrayList<>();
    private ArrayList<MemberName> memberNames = new ArrayList<>();

    String BookID,MemberId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_issue_form);
        init();
    }

    private void init() {
        imgBack = findViewById(R.id.img_back);
        cardDueDate = findViewById(R.id.card_due_date);
        cardIssueDate = findViewById(R.id.card_issue_date);
        cardReceiveDate = findViewById(R.id.card_receive_date);
        txtIssueDate = findViewById(R.id.txt_issue_date);
        txtReceiveDate = findViewById(R.id.txt_receive_date);
        txtStockStatus = findViewById(R.id.txt_stock_status);
        etDueDate = findViewById(R.id.et_due_date);
        pbFormSubmit = findViewById(R.id.pb_form_submit);
        cardSubmitForm = findViewById(R.id.card_submit_form);
        cardCancel = findViewById(R.id.card_cancel);
//        etMemberCode = findViewById(R.id.et_member_code);
        atMemberCode = findViewById(R.id.at_member_code);
        atMemberName = findViewById(R.id.at_member_name);
//        etMemberName = findViewById(R.id.et_member_name);
        etBookNo = findViewById(R.id.et_book_no);
        etBookName = findViewById(R.id.et_book_name);
        etBookName.setEnabled(false);
        etCondition = findViewById(R.id.et_condition);
        etCondition.setEnabled(false);
        etRemark = findViewById(R.id.et_remark);
        txtMemberMobile = findViewById(R.id.txt_member_mobile);

        imgBack.setOnClickListener(this);
        cardIssueDate.setOnClickListener(this);
        cardReceiveDate.setOnClickListener(this);
        etDueDate.setOnEditorActionListener(this);
        cardSubmitForm.setOnClickListener(this);
        cardCancel.setOnClickListener(this);

        etBookNo.setOnFocusChangeListener(this);

        atMemberCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                getMemberCode(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        etBookNo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                getBookNo();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

//        atMemberName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                getMemberCode(charSequence.toString());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDate = c.get(Calendar.DAY_OF_MONTH);


    }

    private void showDatePicker(final String fromWhere) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        switch (fromWhere) {
                            case "IssueDate":
                                selectedDueDate = dayOfMonth;
                                selectedDueMonth = (monthOfYear + 1);
                                selectedDueYear = year;
                                txtIssueDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                break;
                            case "ReceiveDate":
                                String s = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                if (year < selectedDueYear) {
                                    Toast.makeText(BookIssueFormActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                                } else {
                                    if ((monthOfYear + 1) > selectedDueMonth) {
                                        txtReceiveDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                        getCountOfDays(txtIssueDate.getText().toString(), s);
                                    } else {
                                        if (dayOfMonth < selectedDueDate) {
                                            Toast.makeText(BookIssueFormActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();
                                        } else if (dayOfMonth > selectedDueDate && (monthOfYear + 1) < selectedDueMonth) {
                                            Toast.makeText(BookIssueFormActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();
                                        } else {
                                            txtReceiveDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                                            getCountOfDays(txtIssueDate.getText().toString(), s);
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }, currentYear, currentMonth, currentDate);
        datePickerDialog.show();
    }

    private void getDateFromDays() {
        if (!etDueDate.getText().toString().equals("")) {
            int etDueDateText = Integer.parseInt(etDueDate.getText().toString());
            txtIssueDate.setText(currentYear + "-" + (currentMonth + 1) + "-" + currentDate);
            selectedDueDate = currentDate;
            selectedDueMonth = (currentMonth + 1);
            selectedDueYear = currentYear;
            //region date after due date
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, etDueDateText);
            txtReceiveDate.setText(c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DAY_OF_MONTH));
            //endregion
        } else Toast.makeText(this, "Please enter due date", Toast.LENGTH_SHORT).show();

    }

    public void getCountOfDays(String createdDateString, String expireDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDateValue = sdf.parse(createdDateString);
            Date endDateValue = sdf.parse(expireDateString);
            long diff = endDateValue.getTime() - startDateValue.getTime();
            etDueDate.setText(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS) + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    //region API Calling
    private void submitBookIssueForm() {
        pbFormSubmit.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<BookIssueFormResponse> call = apiService.submitBookIssueForm(MemberId, atMemberCode.getText().toString(), BookID, etBookNo.getText().toString(), txtIssueDate.getText().toString().trim() + " 00:00:00", txtReceiveDate.getText().toString().trim() + " 00:00:00", Constant.BHANDER_ID, etRemark.getText().toString());
        call.enqueue(new Callback<BookIssueFormResponse>() {
            @Override
            public void onResponse(Call<BookIssueFormResponse> call, Response<BookIssueFormResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        BookIssueFormResponse bookIssueFormResponse = response.body();
                        List<BookIssueFormResponse.Datum> datumList = bookIssueFormResponse.getData();
                        for (BookIssueFormResponse.Datum datum : datumList) {
                            Log.e(TAG, "onResponse: " + datum.data);
                        }
                        Toast.makeText(BookIssueFormActivity.this, "Form submitted", Toast.LENGTH_SHORT).show();
                        finish();
//                        overridePendingTransition(0,0);

                    } else
                        Toast.makeText(BookIssueFormActivity.this, "OUT OF STOCK", Toast.LENGTH_LONG).show();
                } else
                    Toast.makeText(BookIssueFormActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                pbFormSubmit.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookIssueFormResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbFormSubmit.setVisibility(View.GONE);
            }
        });

    }

    private void getMemberCode(String code) {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AdminGetMemberCodeResponse> call = apiService.getMemberCode(code, Constant.BHANDER_ID);
        call.enqueue(new Callback<AdminGetMemberCodeResponse>() {
            @Override
            public void onResponse(Call<AdminGetMemberCodeResponse> call, Response<AdminGetMemberCodeResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        AdminGetMemberCodeResponse adminGetMemberCodeResponse = response.body();

                        if (adminGetMemberCodeResponse.getData().size() != 0) {
                            List<AdminGetMemberCodeResponse.Datum> datumList = adminGetMemberCodeResponse.getData();
                            for (AdminGetMemberCodeResponse.Datum datum : datumList) {
                                Log.e(TAG, "onResponse: " + datum.getMemberCode());
//                                etMemberName.setText(datum.getMemberName());
////                                etMemberMobile.setText(datum.getMemberMobile());
                                arrayListMemberCode.add(datum.getMemberCode());
                                arrayListMemberName.add(datum.getMemberName());
                                arrayListMemberMobile.add(datum.getMemberMobile());
                                arrayListMemberID.add(datum.getMemberId());
                                memberCodes.add(new MemberCode(datum.getMemberCode()));
                            }
                            ArrayAdapter<MemberCode> adapter = new ArrayAdapter<MemberCode>(
                                    BookIssueFormActivity.this, android.R.layout.simple_dropdown_item_1line, memberCodes);
                            atMemberCode.setAdapter(adapter);
                            atMemberCode.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    MemberCode selected = (MemberCode) adapterView.getAdapter().getItem(i);
                                    if (selected.memCode.equals(arrayListMemberCode.get(i))) {
                                        atMemberName.setText(arrayListMemberName.get(i));
                                        txtMemberMobile.setText(arrayListMemberMobile.get(i));

                                        MemberId = arrayListMemberID.get(i);
                                    }
                                }
                            });

//                            ArrayAdapter<MemberName> adapterName = new ArrayAdapter<MemberName>(
//                                    BookIssueFormActivity.this, android.R.layout.simple_dropdown_item_1line, memberNames);
//                            atMemberName.setAdapter(adapterName);
//                            atMemberName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                                @Override
//                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                                    MemberName selected = (MemberName) adapterView.getAdapter().getItem(i);
//                                    if (selected.memName.equals(arrayListMemberName.get(i))) {
//                                        atMemberCode.setText(arrayListMemberCode.get(i));
//                                        txtMemberMobile.setText(arrayListMemberMobile.get(i));
//                                    }
//                                }
//                            });
                        }
                    } else {
                        Toast.makeText(BookIssueFormActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
//                        etMemberName.setText("");
//                        etMemberMobile.setText("");
                    }
                } else {
                    Toast.makeText(BookIssueFormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
//                    etMemberName.setText("");
//                    etMemberMobile.setText("");
                }
            }

            @Override
            public void onFailure(Call<AdminGetMemberCodeResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                Toast.makeText(BookIssueFormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getBookNo() {
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AdminGetBookNoResponse> call = apiService.getBookNo(etBookNo.getText().toString(), Constant.BHANDER_ID);
        call.enqueue(new Callback<AdminGetBookNoResponse>() {
            @Override
            public void onResponse(Call<AdminGetBookNoResponse> call, Response<AdminGetBookNoResponse> response) {
                if (response.body() != null) {
//                    if (response.body().getMessage().equals("success")) {
                    AdminGetBookNoResponse adminGetBookNoResponse = response.body();
                    bookNoStatus = adminGetBookNoResponse.getMessage();
                    txtStockStatus.setVisibility(View.VISIBLE);

                    txtStockStatus.setText(adminGetBookNoResponse.getMessage());
                    List<AdminGetBookNoResponse.Datum> datumList = adminGetBookNoResponse.getData();

//                    if(adminGetBookNoResponse.getMessage().contains("IN STOCK")){
                        for (AdminGetBookNoResponse.Datum datum : datumList) {
                          if(datum.getBook_no().equals(etBookNo.getText().toString().trim())){
                                etBookName.setText(datum.getBook_name());
                                etCondition.setText(datum.get_condition());
                                BookID = datum.getBookId();
                            }
//                        }
                    }
                } else {
                    Toast.makeText(BookIssueFormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    txtStockStatus.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<AdminGetBookNoResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                Toast.makeText(BookIssueFormActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                txtStockStatus.setVisibility(View.GONE);
            }
        });
    }

    //endregion


    @Override
    public void onBackPressed() {


        super.onBackPressed();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.card_due_date:
                showDatePicker("DueDate");
                break;
            case R.id.card_issue_date:
//                if (!etDueDate.getText().toString().equals("")) {
                    showDatePicker("IssueDate");
//                } else
//                    Toast.makeText(BookIssueFormActivity.this, "Please first select Due Date", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_receive_date:
//                if (!txtIssueDate.getText().toString().equals("")) {
                    showDatePicker("ReceiveDate");
//                } else
//                    Toast.makeText(BookIssueFormActivity.this, "Please first select Due Date", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_cancel:
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;
            case R.id.card_submit_form:
                if (bookNoStatus.equals("book not found")) {
                    Toast.makeText(this, "Book No is not found", Toast.LENGTH_LONG).show();
                } else if (atMemberCode.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter Member code", Toast.LENGTH_LONG).show();
                } else if (atMemberName.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter Member name", Toast.LENGTH_LONG).show();
                } else if (etBookNo.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter Book number", Toast.LENGTH_LONG).show();
                } else if (etDueDate.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter Due Date", Toast.LENGTH_LONG).show();
                } else if (txtIssueDate.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter Issue date", Toast.LENGTH_LONG).show();
                } else if (txtReceiveDate.getText().toString().equals("")) {
                    Toast.makeText(this, "Please enter Receive date", Toast.LENGTH_LONG).show();
                } else submitBookIssueForm();
                break;
        }

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        getDateFromDays();
        return false;
    }

    @Override
    public void onFocusChange(View view, boolean hasFocus) {
        switch (view.getId()) {
            case R.id.et_book_no:
                if (!hasFocus) {
//                    getBookNo();
                }
                break;
        }
    }
}
