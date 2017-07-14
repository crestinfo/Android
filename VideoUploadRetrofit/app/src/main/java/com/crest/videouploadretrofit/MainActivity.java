package com.crest.videouploadretrofit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    int REQUEST_TAKE_GALLERY_VIDEO = 100;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.chooseVideo);

        if (ActivityCompat.checkSelfPermission(MainActivity.this, android.Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                //If the user has denied the permission previously your code will come to this block
                //Here you can explain why you need this permission
                //Explain here why you need this permission
                Log.e("TAG", "checkSelfPermission in");
            }

            Log.e("TAG", "checkSelfPermission out");
            //And finally ask for the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 05);
            return;
        }

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pickFromGallery();
            }
        });
    }

    private void pickFromGallery() {
        Intent intent = new Intent();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_PICK);
        startActivityForResult(Intent.createChooser(intent, "Choose Video"), REQUEST_TAKE_GALLERY_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                Uri selectedFile = data.getData();
                // MEDIA GALLERY
                String selectedImagePath = getPath(selectedFile);
                if (selectedImagePath != null) {
                    Intent intent = new Intent(MainActivity.this, UploadVideo.class);
                    intent.putExtra("path", selectedImagePath);
                    intent.putExtra("title", "TestFile");
                    intent.putExtra("userId", "00");
                    Log.e("Selected Image", "onActivityResult: " + selectedImagePath);
                    startActivity(intent);
                }
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Video.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;
    }


}