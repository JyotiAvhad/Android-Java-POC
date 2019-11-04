package com.jkim.shrutsangam.activity;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.AddMemberFormResponse;
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
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMemberFormActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener ,AdapterView.OnItemSelectedListener{

    private static final String TAG = "TAG";
    EditText edtMemberCode,edtMemberName,edtMemberAddress,edtMemberCity,edtMemberState,edtMemberPincode,edtMemberPhone
            ,edtMemberMobile,edtMemberEmail,edtMemberReferences,edtMemberStatus;
    Spinner spMemberCateogry;
    CardView cardBirthDate,cardJoinDate,cardEndDate,cardAddMember,cardCancel;
    TextView txtBirthDate,txtJoinDate,txtEndDate;
    String[] categoryMember = {"Regular","Outsider","Sadhusaint"};
    String MemberCatory = "Regular";
    private ImageView imgBack;
    private ProgressBar pbFormSubmit;
    private int selectedDueDate, selectedDueMonth, selectedDueYear;
    private int currentDate, currentMonth, currentYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member_form);
        init();
    }

    private void init() {
        imgBack = findViewById(R.id.img_back);
        pbFormSubmit = findViewById(R.id.pb_form_submit);
        imgBack.setOnClickListener(this);
        final Calendar c = Calendar.getInstance();
        currentYear = c.get(Calendar.YEAR);
        currentMonth = c.get(Calendar.MONTH);
        currentDate = c.get(Calendar.DAY_OF_MONTH);

        edtMemberCode = findViewById(R.id.edt_member_code);
        edtMemberName = findViewById(R.id.edt_member_name);
        edtMemberAddress = findViewById(R.id.edt_member_address);
        edtMemberCity = findViewById(R.id.edt_member_city);
        edtMemberState = findViewById(R.id.edt_member_state);
        edtMemberPincode = findViewById(R.id.edt_member_pincode);
        edtMemberPhone = findViewById(R.id.edt_member_phone_no);
        edtMemberMobile = findViewById(R.id.edt_member_monbileNo);
        edtMemberEmail = findViewById(R.id.edt_member_email);
        cardBirthDate = findViewById(R.id.card_birthdate);
        txtBirthDate = findViewById(R.id.txtbirthdate);
        edtMemberReferences = findViewById(R.id.edt_refrence);
        spMemberCateogry = findViewById(R.id.spinner);
        spMemberCateogry.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoryMember);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spMemberCateogry.setAdapter(aa);
        cardJoinDate = findViewById(R.id.card_join_date);
        txtJoinDate = findViewById(R.id.txt_join_date);
        cardEndDate = findViewById(R.id.card_end_date);
        txtEndDate = findViewById(R.id.txt_end_date);
        edtMemberStatus = findViewById(R.id.et_member_status);
        cardAddMember = findViewById(R.id.card_add_member);
        cardCancel = findViewById(R.id.card_cancel);

        cardAddMember.setOnClickListener(this);
        cardCancel.setOnClickListener(this);

        cardJoinDate.setOnClickListener(this);
        cardEndDate.setOnClickListener(this);
        cardBirthDate.setOnClickListener(this);




    }

    private void showDatePicker(final String fromWhere) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        switch (fromWhere) {
                            case "JoinDate":
                                selectedDueDate = dayOfMonth;
                                selectedDueMonth = (monthOfYear + 1);
                                selectedDueYear = year;
                                txtJoinDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                break;
                            case "EndDate":
                                String s = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                                if (year < selectedDueYear) {
                                    Toast.makeText(AddMemberFormActivity.this, "Invalid year", Toast.LENGTH_SHORT).show();
                                } else {
                                    if ((monthOfYear + 1) > selectedDueMonth) {
                                        txtEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                        getCountOfDays(txtIssueDate.getText().toString(), s);
                                    } else {
                                        if (dayOfMonth < selectedDueDate) {
                                            Toast.makeText(AddMemberFormActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();
                                        } else if (dayOfMonth > selectedDueDate && (monthOfYear + 1) < selectedDueMonth) {
                                            Toast.makeText(AddMemberFormActivity.this, "Invalid date", Toast.LENGTH_SHORT).show();
                                        } else {
                                            txtEndDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
//                                            getCountOfDays(txtIssueDate.getText().toString(), s);
                                        }
                                    }
                                }
                                break;

                            case "BirthDate":
                                txtBirthDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                                break;

                        }
                    }
                }, currentYear, currentMonth, currentDate);
        datePickerDialog.show();
    }


    //region API Calling
    private void submitAddMemberform() {

        pbFormSubmit.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<AddMemberFormResponse> call = apiService.submitaddMemberForm(edtMemberCode.getText().toString().trim()
                ,edtMemberName.getText().toString().trim(), edtMemberAddress.getText().toString().trim(),
                edtMemberCity.getText().toString().trim(), edtMemberState.getText().toString().trim(),
                edtMemberPincode.getText().toString().trim(),edtMemberPhone.getText().toString().trim()
                ,edtMemberMobile.getText().toString().trim(),edtMemberEmail.getText().toString().trim(),
                txtBirthDate.getText().toString().trim(), edtMemberReferences.getText().toString().trim(),MemberCatory,
                txtJoinDate.getText().toString().trim(),txtEndDate.getText().toString().trim(),
                edtMemberStatus.getText().toString().trim(),Constant.BHANDER_ID);

        call.enqueue(new Callback<AddMemberFormResponse>() {
            @Override
            public void onResponse(Call<AddMemberFormResponse> call, Response<AddMemberFormResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        Toast.makeText(AddMemberFormActivity.this, "Form submitted", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {
                        Toast.makeText(AddMemberFormActivity.this, "OUT OF STOCK", Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(AddMemberFormActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
                pbFormSubmit.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AddMemberFormResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t);
                pbFormSubmit.setVisibility(View.GONE);
            }
        });

    }



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
            case R.id.card_join_date:
//                if (!etDueDate.getText().toString().equals("")) {
                    showDatePicker("JoinDate");
//                } else
//                    Toast.makeText(BookIssueFormActivity.this, "Please first select Due Date", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_end_date:
//                if (!txtIssueDate.getText().toString().equals("")) {
                    showDatePicker("EndDate");
//                } else
//                    Toast.makeText(BookIssueFormActivity.this, "Please first select Due Date", Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_birthdate:
                showDatePicker("BirthDate");
                break;
            case R.id.card_cancel:
                Intent intent = getIntent();
                overridePendingTransition(0, 0);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;
            case R.id.card_add_member:
                if (edtMemberCode.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member code", Toast.LENGTH_LONG).show();
                } else if (edtMemberName.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member name", Toast.LENGTH_LONG).show();
                } else if (edtMemberAddress.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member Address", Toast.LENGTH_LONG).show();
                } else if (edtMemberCity.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member City", Toast.LENGTH_LONG).show();
                } else if (edtMemberState.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member State", Toast.LENGTH_LONG).show();
                } else if (edtMemberPincode.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member Pincode", Toast.LENGTH_LONG).show();
                } else if (edtMemberMobile.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member MobileNo", Toast.LENGTH_LONG).show();
                }else if (edtMemberEmail.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member Email", Toast.LENGTH_LONG).show();
                }else if (txtBirthDate.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member BirthDate", Toast.LENGTH_LONG).show();
                }else if (txtJoinDate.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Join date", Toast.LENGTH_LONG).show();
                }else if (txtEndDate.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter End date", Toast.LENGTH_LONG).show();
                }else if (edtMemberStatus.getText().toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter Member Status", Toast.LENGTH_LONG).show();
                }else submitAddMemberform();
                break;
        }

    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
//        getDateFromDays();
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        MemberCatory = categoryMember[i];

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
