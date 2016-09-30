package com.utiset.muffin.muffin;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.crashlytics.android.Crashlytics;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;
import com.utiset.muffin.models.UserModel;
import com.utiset.muffin.services.PollService;

import io.fabric.sdk.android.Fabric;

public class LandingActivity extends AppCompatActivity {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "bBMPUEj540pVerdemVbsVEKgm";
    private static final String TWITTER_SECRET = "OvwpobwztRdHq8Nz2GDTcRQtKiUiobQyoQcY9F1LdF2mMzNfHU";

    private UserModel userModel = new UserModel();

    private TwitterLoginButton twitterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //once application launches start the poll service
//        startService(new Intent(this.getApplicationContext(), PollService.class));


        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Crashlytics(), new Twitter(authConfig));

        TwitterSession session = Twitter.getSessionManager().getActiveSession();
        if (session != null) {
            startActivity(new Intent(getApplicationContext(), FeedActivity.class));
            finish();
        }

        setContentView(R.layout.activity_landing);
        initComponents();
    }

    private void initComponents() {

        twitterButton = (TwitterLoginButton) findViewById(R.id.landing_twitter_button);


        if (twitterButton != null) {
            twitterButton.setCallback(new Callback<TwitterSession>() {
                @Override
                public void success(Result<TwitterSession> result) {
                    userModel.setUsername(result.data.getUserName());
                    userModel.setProvider(getString(R.string.twitter_provider));
                    userModel.setUserId(String.valueOf(result.data.getUserId()));
                    userModel.setToken(result.data.getAuthToken().token);

                    getTwitterEmail(result.data);

                }

                @Override
                public void failure(TwitterException exception) {
                    // Do something on failure
                }

            });
        }
    }

    private void getTwitterEmail(TwitterSession session){
        TwitterAuthClient authClient = new TwitterAuthClient();
        authClient.requestEmail(session, new Callback<String>() {
            @Override
            public void success(Result<String> result) {
                // Do something with the result, which provides the email address
                userModel.setEmail(result.response.message());
                startActivity(new Intent(getApplicationContext(), FeedActivity.class));
                finish();
            }

            @Override
            public void failure(TwitterException exception) {
                userModel.setEmail(null);
                startActivity(new Intent(getApplicationContext(), FeedActivity.class));
                finish();
            }
        });
    }

    private View.OnClickListener loginButtonInterface(final Context context){
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(context, FeedActivity.class));
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result to the login button.
        twitterButton.onActivityResult(requestCode, resultCode, data);
    }
}
