package com.jkim.shrutsangam.fragment;


import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.jkim.shrutsangam.R;
import com.jkim.shrutsangam.api.ApiClient;
import com.jkim.shrutsangam.api.ApiInterface;
import com.jkim.shrutsangam.api.modal.BookUoloadResponse;
import com.jkim.shrutsangam.utils.Constant;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.jkim.shrutsangam.activity.ImagePickerActivity;
import com.jkim.shrutsangam.utils.interfaces.PickerOptionListener;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class BookEntryFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = BookEntryFragment.class.getSimpleName();
    public static final int REQUEST_IMAGE = 100;
    private ImageView imgProfile, imgPlus;
    private EditText etBooktitle;
    private CardView cardUpload;
    private ProgressBar pbUploadImage;
    private String selectedImageUrl;
    ImageView imgBookImage;

    public BookEntryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book_entry, container, false);
        init(view);
        loadProfileDefault();
        ImagePickerActivity.clearCache(getContext());
        return view;
    }

    private void init(View view) {
        imgProfile = view.findViewById(R.id.img_profile);
        imgPlus = view.findViewById(R.id.img_plus);
        etBooktitle = view.findViewById(R.id.et_booktitle);
        cardUpload = view.findViewById(R.id.card_upload);
        pbUploadImage = view.findViewById(R.id.pb_upload_image);

        imgProfile.setOnClickListener(this);
        imgPlus.setOnClickListener(this);
        cardUpload.setOnClickListener(this);
        imgBookImage = view.findViewById(R.id.imgBook);
        imgBookImage.setOnClickListener(this);
    }

    private void loadProfile(Uri url) {
        Log.d(TAG, "Image cache path: " + url);

        imgBookImage.setImageURI(url);
        Picasso.get().load(url).into(imgBookImage);
//        imgProfile.setColorFilter(ContextCompat.getColor(getContext(), android.R.color.transparent));
        selectedImageUrl = url.getPath();
    }

    private void loadProfileDefault() {
        Picasso.get().load(R.drawable.ic_profile).into(imgProfile);
        imgProfile.setColorFilter(ContextCompat.getColor(getContext(), R.color.profileDefaultTint));
    }

    private void onProfileImageClick() {
        Dexter.withActivity(getActivity())
                .withPermissions(Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        if (report.areAllPermissionsGranted()) {
                            showImagePickerOptions();
                        }

                        if (report.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).check();
    }

    private void showImagePickerOptions() {
        ImagePickerActivity.showImagePickerOptions(getContext(), new PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {





        Intent intent = new Intent(getContext(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagePickerActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagePickerActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(getContext(), ImagePickerActivity.class);
        intent.putExtra(ImagePickerActivity.INTENT_IMAGE_PICKER_OPTION, ImagePickerActivity.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagePickerActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagePickerActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, REQUEST_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");
                loadProfile(uri);

                Log.e(TAG, "onActivityResult: " + data.hasExtra("path"));

//
//                if (data != null) {
//
//                    Uri selectedImage = data.getData();
//                    String[] filePath = {MediaStore.Images.Media.DATA};
//                    Cursor c = getContext().getContentResolver().query(selectedImage, filePath,
//                            null, null, null);
//                    c.moveToFirst();
//                    int columnIndex = c.getColumnIndex(filePath[0]);
//                    Log.e(TAG, "onActivityResult: " + c.getString(columnIndex));
//                    c.close();
//
//
//                }

            }
        }
    }

    /**
     * Showing Alert Dialog with Settings option
     * Navigates user to app settings
     * NOTE: Keep proper title and message depending on your app
     */
    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle(getString(R.string.dialog_permission_title));
        builder.setMessage(getString(R.string.dialog_permission_message));
        builder.setPositiveButton(getString(R.string.go_to_settings), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                BookEntryFragment.this.openSettings();
            }
        });
        builder.setNegativeButton(getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }

    private void uploadImage() {
        pbUploadImage.setVisibility(View.VISIBLE);
        File file = new File(selectedImageUrl);
        String name = file.getName();
//        int compressionRatio = 2;
//        try {
//            Bitmap bitmap = BitmapFactory.decodeFile(file.getPath());
//            bitmap.compress(Bitmap.CompressFormat.JPEG, compressionRatio, new FileOutputStream(file));
//        } catch (Throwable t) {
//            Log.e("ERROR", "Error compressing file." + t.toString());
//            t.printStackTrace();
//        }
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("bookImage", file.getName(), requestFile);

// add another part within the multipart request
        RequestBody userId =
                RequestBody.create(MediaType.parse("multipart/form-data"), Constant.BHANDER_ID);
        RequestBody title =
                RequestBody.create(MediaType.parse("multipart/form-data"), etBooktitle.getText().toString());

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);

        Call<BookUoloadResponse> call = apiService.bookUpload(userId, title, body);
        call.enqueue(new Callback<BookUoloadResponse>() {
            @Override
            public void onResponse(Call<BookUoloadResponse> call, Response<BookUoloadResponse> response) {
                if (response.body() != null) {
                    Log.e(TAG, "onResponse: " + response);
                    if (response.body().getMessage().equals("success")) {
                        BookUoloadResponse bookUoloadResponse = response.body();
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbUploadImage.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<BookUoloadResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.toString());
                Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                pbUploadImage.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.img_profile:
            case R.id.img_plus:
            case R.id.imgBook:
                onProfileImageClick();
                break;
            case R.id.card_upload:
                if (etBooktitle.getText().toString().equals("")) {
                    Toast.makeText(getContext(), "Please enter book title", Toast.LENGTH_SHORT).show();
                } else uploadImage();
                break;
        }
    }
}
