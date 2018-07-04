package com.crest.videouploadretrofit;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadVideo extends AppCompatActivity {
    TextView txtFile;
    Button btnUpload;
    String filePath, duration, title, userid;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_video);
        txtFile = (TextView) findViewById(R.id.txtVideoFile);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        progressBar.setVisibility(View.GONE);
        filePath = getIntent().getStringExtra("path");
        duration = getDuration();
        title = getIntent().getStringExtra("title");
        userid = getIntent().getStringExtra("userId");
        Log.e("DATA", "file " + filePath + "\n" + "duration " + duration + "\n" + "Title " + title + "\n" + "userid " + userid + "\n");

        txtFile.setText(filePath);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadVideoFile();
            }
        });
    }

    String getDuration() {
        MediaPlayer mp = MediaPlayer.create(this, Uri.parse(filePath));
        int duration = mp.getDuration();
        mp.release();
        return String.format("%d min, %d sec",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );
    }

    private void uploadVideoFile() {
        progressBar.setVisibility(View.VISIBLE);
        btnUpload.setVisibility(View.GONE);
        File file = new File(String.valueOf(filePath));
        final RequestBody requestBody = RequestBody.create(MediaType.parse("video"), file);
        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), "00");
        RequestBody v_title = RequestBody.create(MediaType.parse("text/plain"), title);
        RequestBody duration = RequestBody.create(MediaType.parse("text/plain"), "00");
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("v_video", file.getName(), requestBody);
        ApiCallInterface anInterface = ApiClient.getClient().create(ApiCallInterface.class);

        final Call<UploadVideoPojo> updateProfileCall = anInterface.uploadData(id, v_title,duration, fileToUpload);
        updateProfileCall.enqueue(new Callback<UploadVideoPojo>() {
            @Override
            public void onResponse(Call<UploadVideoPojo> call, Response<UploadVideoPojo> response) {
                progressBar.setVisibility(View.GONE);
                btnUpload.setVisibility(View.VISIBLE);
                Toast.makeText(UploadVideo.this, "Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<UploadVideoPojo> call, Throwable t) {
                Log.e("Error", "onFailure: In Update Profile" + t);
                progressBar.setVisibility(View.GONE);
                btnUpload.setVisibility(View.VISIBLE);
            }
        });
    }
}
