package com.example.chatsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class StatusFragment extends Fragment {

    private static final String TAG = "StatusFragment";
//    private Button btnTEST;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Log.d(TAG, "onCreateView: Status fragment");

        View view=inflater.inflate(R.layout.status_fragment,container,false);
//
//        btnTEST = (Button) view.findViewById(R.id.btnTEST);
//
//        btnTEST.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getActivity(), "TESTING BUTTON CLICK 1",Toast.LENGTH_SHORT).show();
//            }
//        });

        return view;
    }
}
