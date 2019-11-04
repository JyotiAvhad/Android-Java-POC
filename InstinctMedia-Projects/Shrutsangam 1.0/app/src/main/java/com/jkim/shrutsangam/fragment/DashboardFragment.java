package com.jkim.shrutsangam.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.activity.DashboardActivity;
import com.jkim.shrutsangam.adapter.BookListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.BookListResponse;
import com.jkim.shrutsangam.api.modal.DashBoardResponse;
import com.jkim.shrutsangam.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment implements View.OnClickListener {

    private TextView txtDashboardBhandarName,txtDashboardTodaybook, txtDashboardMember, txtDashboardOutstanding, txtDashboardReceiveBook, txtDashboardIssue;
    private CardView cardTotalBooks, cardTotalMembers, cardTotalIssueBook, cardTotalReceiveBook, cardTotalOutstanding;
    private LinearLayout llMainDashboard;
    SharedPreferences prefIssueList , prefReceiveList;
    SharedPreferences.Editor editorIssueList,editorReceiveList;

    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        txtDashboardTodaybook = view.findViewById(R.id.txt_dashboard_todaybook);
        txtDashboardMember = view.findViewById(R.id.txt_dashboard_member);
        txtDashboardOutstanding = view.findViewById(R.id.txt_dashboard_outstanding);
        txtDashboardReceiveBook = view.findViewById(R.id.txt_dashboard_receive_book);
        txtDashboardIssue = view.findViewById(R.id.txt_dashboard_issue);
        cardTotalBooks = view.findViewById(R.id.card_total_books);
        cardTotalMembers = view.findViewById(R.id.card_total_members);
        cardTotalIssueBook = view.findViewById(R.id.card_total_issue_book);
        cardTotalReceiveBook = view.findViewById(R.id.card_total_receive_book);
        cardTotalOutstanding = view.findViewById(R.id.card_total_outstanding);
        llMainDashboard = view.findViewById(R.id.ll_main_dashboard);

        txtDashboardBhandarName = view.findViewById(R.id.txt_bhandar_name);
        txtDashboardTodaybook.setText(Constant.DASHBOARD_BOOK_ENTRY);
        txtDashboardMember.setText(Constant.DASHBOARD_MEMBER);
        txtDashboardOutstanding.setText(Constant.DASHBOARD_OUTSTANDING);
        txtDashboardReceiveBook.setText(Constant.DASHBOARD_RECEIVE_BOOK);
        txtDashboardIssue.setText(Constant.DASHBOARD_ISSUE_BOOK);
        txtDashboardBhandarName.setText(Constant.BHANDAR_NAME);



        cardTotalBooks.setOnClickListener(this);
        cardTotalMembers.setOnClickListener(this);
        cardTotalIssueBook.setOnClickListener(this);
        cardTotalReceiveBook.setOnClickListener(this);
        cardTotalOutstanding.setOnClickListener(this);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_dashboard, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.card_total_books:
                loadFragment(new BookListFragment());
                break;
            case R.id.card_total_members:
                loadFragment(new AdminMemberListFragment());
                break;
            case R.id.card_total_issue_book:
                loadFragment(new IssueBookListFragment());
                break;
            case R.id.card_total_receive_book:
                loadFragment(new BookReceiveFragment());
                break;
            case R.id.card_total_outstanding:
                loadFragment(new OutstandingListFragment());
                break;
//            case R.id.card_total_pending_book:
//                loadFragment(new PendingListFragment());
//                break;
        }
    }
}
