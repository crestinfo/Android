package com.example.server.getfbdataapp;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.widget.LoginButton;
import com.facebook.login.widget.ProfilePictureView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {
    CallbackManager callbackManager;
    Button btnUpload, btnDetails;
    LoginButton btnLogin;
    ProfilePictureView imgProfilePic;
    Dialog dialog_details;
    TextView details_txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);

        callbackManager = CallbackManager.Factory.create();
        imgProfilePic = (ProfilePictureView) findViewById(R.id.img_picture);
        btnLogin = (LoginButton) findViewById(R.id.login_button);
        btnUpload = (Button) findViewById(R.id.btnUpload);
        btnDetails = (Button) findViewById(R.id.btnDetails);
        btnLogin.setReadPermissions("public_profile email");
        dialog_details = new Dialog(this);
        dialog_details.setContentView(R.layout.layout_dialog_details);
        dialog_details.setTitle("Details");
        details_txt = (TextView) dialog_details.findViewById(R.id.txtUserDetails);
        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_details.show();
            }
        });

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.yourappname.app",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", "KeyHash:" + Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));
                Toast.makeText(getApplicationContext(), Base64.encodeToString(md.digest(),
                        Base64.DEFAULT), Toast.LENGTH_LONG).show();
            }
        } catch (PackageManager.NameNotFoundException e) {

        } catch (NoSuchAlgorithmException e) {

        }


        if (AccessToken.getCurrentAccessToken() != null) {
            RequestData();
            btnDetails.setVisibility(View.VISIBLE);
            btnUpload.setVisibility(View.VISIBLE);
        }
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (AccessToken.getCurrentAccessToken() != null) {
                    imgProfilePic.setProfileId(null);
                }
            }
        });
    }

    public void RequestData() {
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {

                JSONObject json = response.getJSONObject();
                try {
                    if (json != null) {
                        String text = "<b>Name :</b> " + json.getString("name") + "<br><br><b>Email :</b> " + json.getString("email") + "<br><br><b>Profile link :</b> " + json.getString("link");
                        details_txt.setText(Html.fromHtml(text));
                        imgProfilePic.setProfileId(json.getString("id"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,email,picture");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
