package com.jkim.shrutsangam.fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.adapter.ImageListAdapter;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.ImageDeleteResponse;
import com.jkim.shrutsangam.api.modal.ImageListResponse;
import com.jkim.shrutsangam.utils.Constant;
import com.jkim.shrutsangam.utils.interfaces.OnDeleteImageClick;
import com.jkim.shrutsangam.utils.interfaces.OnDialogActionClick;
import com.jkim.shrutsangam.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageListFragment extends Fragment {
    private ProgressBar pbImageList;
    private RecyclerView rvImageList;
    private ImageListAdapter imageListAdapter;

    public ImageListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        init(view);
        getImageList();
        return view;
    }

    private void init(View view) {
        pbImageList = view.findViewById(R.id.pb_image_list);
        rvImageList = view.findViewById(R.id.rv_image_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvImageList.setLayoutManager(gridLayoutManager);
    }

    private void getImageList() {
        pbImageList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ImageListResponse> imageListResponseCall = apiService.getImageList(Constant.BHANDER_ID);
        imageListResponseCall.enqueue(new Callback<ImageListResponse>() {
            @Override
            public void onResponse(Call<ImageListResponse> call, Response<ImageListResponse> response) {
                if (response.body() != null) {
                    if (response.body().getMessage().equals("success")) {
                        ImageListResponse imageListResponse = response.body();
                        try {
                            List<ImageListResponse.Datum> datumList = imageListResponse.getData();
                            for (ImageListResponse.Datum datum : datumList) {
                                Log.e(TAG, "onResponse: " + datum.listrec);
                                imageListAdapter = new ImageListAdapter(datum.listrec, getContext(), new OnDeleteImageClick() {
                                    @Override
                                    public void onDeleteImageClick(final String imgId) {
                                        Utils utils = new Utils();
                                        utils.alertDialog("Are you sure want to delete this image ?", getString(R.string.app_name), getContext(), new OnDialogActionClick() {
                                            @Override
                                            public void onDialogYes() {
                                                imageDelete(imgId);
                                            }

                                            @Override
                                            public void onDialogNo() {
                                            }
                                        });
                                    }
                                });
                            }
                            rvImageList.setAdapter(imageListAdapter);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbImageList.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ImageListResponse> call, Throwable t) {
                pbImageList.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void imageDelete(String imgId) {
        pbImageList.setVisibility(View.VISIBLE);
        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<ImageDeleteResponse> imageDeleteResponseCall = apiService.imageDelete(Constant.USER_ID, imgId);
        imageDeleteResponseCall.enqueue(new Callback<ImageDeleteResponse>() {
            @Override
            public void onResponse(Call<ImageDeleteResponse> call, Response<ImageDeleteResponse> response) {
                if (response.body().getMessage().equals("success")) {
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                pbImageList.setVisibility(View.GONE);
                getImageList();
            }

            @Override
            public void onFailure(Call<ImageDeleteResponse> call, Throwable t) {
                pbImageList.setVisibility(View.GONE);
            }
        });
    }


}
