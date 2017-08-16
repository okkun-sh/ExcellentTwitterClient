package com.example.sho.excellent_twitter_client;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import twitter4j.TwitterException;
import twitter4j.auth.OAuthAuthorization;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationContext;

public class MainActivity extends AppCompatActivity {
    public static RequestToken _req = null;
    public static OAuthAuthorization _oauth = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button sendButton = (Button) findViewById(R.id.loginButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                executeOauth();
            }
        });
    }

    private void executeOauth() {

        Configuration conf = ConfigurationContext.getInstance();

        _oauth = new OAuthAuthorization(conf);

        _oauth.setOAuthConsumer("*****", "*****");

        try {
            _req = _oauth.getOAuthRequestToken("Callback://SubActivity");
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        String _uri;
        _uri = _req.getAuthorizationURL();
        startActivityForResult(new Intent(Intent.ACTION_VIEW , Uri.parse(_uri)), 0);
    }
}
