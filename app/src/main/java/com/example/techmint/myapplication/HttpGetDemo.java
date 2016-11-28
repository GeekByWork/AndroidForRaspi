package com.example.techmint.myapplication;

/**
 * Created by techmint on 11/28/16.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.TextView;

public class HttpGetDemo extends AsyncTask<TextView, Void, String> {
    Context context;
    TextView t;
    String result = "fail";

    HttpGetDemo(Context context){
        this.context=context;
    }

    @Override
    protected String doInBackground(TextView... params) {
        // TODO Auto-generated method stub
        this.t = params[0];
        return GetSomething();
    }

    final String GetSomething()
    {
        String url = "http://www.google.com";
        BufferedReader inStream = null;
        try {
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpRequest = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpRequest);
            inStream = new BufferedReader(
                    new InputStreamReader(
                            response.getEntity().getContent()));

            StringBuffer buffer = new StringBuffer("");
            String line = "";
            String NL = System.getProperty("line.separator");
            while ((line = inStream.readLine()) != null) {
                buffer.append(line + NL);
            }
            inStream.close();

            result = buffer.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    protected void onPostExecute(String page)
    {
        CommonUtilities.loginResponse = page;
        context.startActivity(new Intent(context, MainActivity.class));
    }
}