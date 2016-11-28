package com.example.techmint.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

public class Login extends AppCompatActivity {

    Button bLogin;
    EditText etUsername, etPassword;
    TextView tvRegisterLink;
    UserLocalStore userLocalStore;
    final String httpPath = "http://google.com";
    String o = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.userName);
        etPassword = (EditText) findViewById(R.id.password);
        bLogin = (Button) findViewById(R.id.btnLogin);
        tvRegisterLink = (TextView) findViewById(R.id.registerLink);
        userLocalStore = new UserLocalStore(this);
    }

    public void btnloginClick(View view) {
        HttpGetDemo task = new HttpGetDemo(Login.this);
        task.execute(tvRegisterLink);
        /*
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(httpPath);
        try
        {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();
        }
        catch (ClientProtocolException e){
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        */

        /*
        User user = new User(-1, null, null);
        userLocalStore.storeUserData(user);
        userLocalStore.setUserLoggedIn(true);
        HttpURLConnection urlConnection = null;
        try {
            String link = "http://google.com";
            URL url = new URL(link);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(10000 );
            urlConnection.setConnectTimeout(15000 );
            urlConnection.setDoOutput(true);
            urlConnection.connect();
            BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
            char[] buffer = new char[1024];
            String jsonString = new String();
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            br.close();
            jsonString = sb.toString();
            System.out.println("JSON: " + jsonString);
            JSONObject obj = new JSONObject(jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }


    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onStop() {
        super.onStop();

    }

    public boolean b = true;
    public JSONArray att = null;

    public void bntClickLogin(View v) {
        User user = new User(-1, null, null);
        userLocalStore.storeUserData(user);
        userLocalStore.setUserLoggedIn(true);

        class Test implements Runnable {
            public void run() {
                try {
                    HttpURLConnection urlConnection = null;
                    String link = "http://google.com";
                    URL url = new URL(link);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setReadTimeout(10000 /* milliseconds */);
                    urlConnection.setConnectTimeout(15000 /* milliseconds */);
                    urlConnection.setDoOutput(true);
                    urlConnection.connect();
                    BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
                    char[] buffer = new char[1024];
                    String jsonString = new String();
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    jsonString = sb.toString();
                    System.out.println("JSON: " + jsonString);
                    JSONObject obj = new JSONObject("{'isError': 'true','error': 'Login Failed','attendance':	[{'date': '12/19/2016','attendance': 'present'},{'date': '12/19/2016','attendance': 'present'}]}");
                    String isError = obj.getString("isError");
                    String error = obj.getString("error");
                    att = obj.getJSONArray("attendance");
                    for (int i = 0; i < att.length(); i++) {
                        JSONObject a = att.getJSONObject(i);
                    }
                    b = false;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        Thread t = new Thread(new Test());
        t.start();
        while (b) {
        }
        Intent mIntent = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putString("ATTENDANCE", att.toString());
        mIntent.putExtras(b);
        startActivity(mIntent);
    }
}
