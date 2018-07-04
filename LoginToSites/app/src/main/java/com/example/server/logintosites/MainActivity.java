package com.example.server.logintosites;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.linkedin.platform.APIHelper;
import com.linkedin.platform.LISessionManager;
import com.linkedin.platform.errors.LIApiError;
import com.linkedin.platform.errors.LIAuthError;
import com.linkedin.platform.listeners.ApiListener;
import com.linkedin.platform.listeners.ApiResponse;
import com.linkedin.platform.listeners.AuthListener;
import com.linkedin.platform.utils.Scope;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.fabric.sdk.android.Fabric;

public class MainActivity extends AppCompatActivity {
    LoginButton btnFacebook;
    Button btnLinkedin;
    CallbackManager callbackManager;
    TwitterLoginButton twitterLoginButton;
    static String TAG = "MainActivity";
    static String TWITTER_KEY = "erPTkJojKxEkJ7pC2nBhkmPGM";
    static String TWITTER_SECRET = "F7RXjO2ceB66yEEpEJBuRPZk9rdONzk4LR6VV2UwkClkq9fwkW";
    String name, email;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);
        btnFacebook = (LoginButton) findViewById(R.id.btnFacebook);
        twitterLoginButton = (TwitterLoginButton) findViewById(R.id.twitterLogin);
        btnLinkedin = (Button) findViewById(R.id.btnLinkedin);
        callbackManager = CallbackManager.Factory.create();


        btnFacebook.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                final Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                try {
                                    Log.e("Facebook Reponse", "onCompleted: " + object.toString());
                                    intent.putExtra("tag", "facebook");
                                    intent.putExtra("name", object.getString("name"));
                                    intent.putExtra("email", response.getJSONObject().get("email").toString());
                                    Log.e("Facebook Email", "onCompleted: " + response.getJSONObject().get("email").toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                startActivity(intent);
                                finish();
                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                Log.e(TAG, "onCancel: ");
            }

            @Override
            public void onError(FacebookException error) {
                Log.e(TAG, "onError: " + error);
            }
        });

        twitterLoginButton.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                name = result.data.getUserName();
                Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                intent.putExtra("tag", "twitter");
                intent.putExtra("name", name);
                intent.putExtra("email", "null");
                startActivity(intent);
                finish();
            }

            @Override
            public void failure(TwitterException exception) {
                Log.e(TAG, "failure: Twitter" + exception);
            }
        });
        btnLinkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LISessionManager.getInstance(getApplicationContext())
                        .init(MainActivity.this, buildScope(), new AuthListener() {
                            @Override
                            public void onAuthSuccess() {

                                String url = "https://api.linkedin.com/v1/people/~";
                                final APIHelper apiHelper = APIHelper.getInstance(getApplicationContext());
                                apiHelper.getRequest(MainActivity.this, url, new ApiListener() {
                                    @Override
                                    public void onApiSuccess(ApiResponse apiResponse) {
                                        JSONObject jsonObject = apiResponse.getResponseDataAsJson();
                                        try {
                                            name = jsonObject.getString("firstName");
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
                                        intent.putExtra("tag", "linkedin");
                                        intent.putExtra("name", name);
                                        intent.putExtra("email", "null");
                                        startActivity(intent);
                                        finish();
                                    }

                                    @Override
                                    public void onApiError(LIApiError liApiError) {
                                        Log.e(TAG, "onApiError: Error making GET request Linked In!");
                                    }
                                });

                            }
                            @Override
                            public void onAuthError(LIAuthError error) {
                                Log.e(TAG, "onAuthError: Linked In " + error);
                            }
                        }, true);
            }
        });
    }


    private static Scope buildScope() {
        return Scope.build(Scope.R_BASICPROFILE, Scope.R_EMAILADDRESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        twitterLoginButton.onActivityResult(requestCode, resultCode, data);
        LISessionManager.getInstance(getApplicationContext())
                .onActivityResult(this,
                        requestCode, resultCode, data);
    }

    void generateKayHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.server.logintosites",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            Log.d(TAG, e.getMessage(), e);
        }
    }
}
