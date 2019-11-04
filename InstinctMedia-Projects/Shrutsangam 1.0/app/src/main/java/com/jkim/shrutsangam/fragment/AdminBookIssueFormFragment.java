package com.jkim.shrutsangam.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jkim.shrutsangam.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminBookIssueFormFragment extends Fragment {


    public AdminBookIssueFormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_admin_book_issue_form, container, false);

        return view;
    }

}
