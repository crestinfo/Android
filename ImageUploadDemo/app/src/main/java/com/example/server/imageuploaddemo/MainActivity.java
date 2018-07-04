package com.example.server.imageuploaddemo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.server.imageuploaddemo.POJO.UpdateProfile.SbResponse;
import com.example.server.imageuploaddemo.POJO.UpdateProfile.UpdateProfile;
import com.example.server.imageuploaddemo.POJO.UserData;
import com.example.server.imageuploaddemo.POJO.UserData_;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnChoose, btnUpload;
    ImageView imgView;
    private int PICK_IMAGE_REQUEST = 100;
    UserData_ userData;
    SbResponse updateProfile;
    ProgressDialog progressDialog;
    String filePath;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnChoose = (Button) findViewById(R.id.btnChooseImage);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        imgView = (ImageView) findViewById(R.id.imgView);

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    100);
        }


        btnChoose.setOnClickListener(this);
        btnUpload.setOnClickListener(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait");


        userData = new UserData_();
        updateProfile = new SbResponse();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnChooseImage:
                showFileChooser();
                break;

            case R.id.btnUpload:
                progressDialog.show();
                UpdateUserProfile();
                break;
        }
    }

    //Show File Chooser for Image
    private void showFileChooser() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri selectedImageURI = data.getData();
            filePath = String.valueOf(new File(getRealPathFromURI(selectedImageURI)));
            imgView.setImageURI(Uri.parse(filePath));
        }
    }


    //Update User Information via retrofit call
    private void UpdateUserProfile() {

        File file = new File(String.valueOf(filePath));
        Log.e("FILE", "UpdateUserProfile: " + file);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), "00");
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
        ApiCallInterface anInterface = ApiClient.getClient().create(ApiCallInterface.class);

        final Call<UpdateProfile> updateProfileCall = anInterface.uploadProfile(id, fileToUpload);
        Log.e("updateProfileCall", "UpdateUserProfile: " + updateProfileCall);
        updateProfileCall.enqueue(new Callback<UpdateProfile>() {
            @Override
            public void onResponse(Call<UpdateProfile> call, Response<UpdateProfile> response) {
                Toast.makeText(MainActivity.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
                getUserProfile();
            }

            @Override
            public void onFailure(Call<UpdateProfile> call, Throwable t) {
                Log.e("Error", "onFailure: In Update Profile" + t);
            }
        });
    }


    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

    //Get User Data From Server
    public void getUserProfile() {
        ApiCallInterface anInterface = ApiClient.getClient().create(ApiCallInterface.class);
        Call<UserData> dataCall = anInterface.getUser("00");
        dataCall.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                userData = response.body().getSbResponse().getUserData();
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                Log.e("Error", "onFailure: to get user info :- " + t);
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Internet Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}