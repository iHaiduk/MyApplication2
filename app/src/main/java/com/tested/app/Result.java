package com.tested.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Ihor on 30.11.2014.
 */
public class Result extends Activity {

    private String answer;
    private String uid_test;

    private TextView answerT;
    private TextView count;
    private TextView procent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        answer = getIntent().getStringExtra("answer");
        uid_test = getIntent().getStringExtra("uid_test");

        answerT = (TextView) findViewById(R.id.answerT);
        count = (TextView) findViewById(R.id.count);
        procent = (TextView) findViewById(R.id.procent);

        new HttpAsyncTask().execute("http://kursova.esy.es/test/result/id/"+uid_test+"/answ/"+answer);
    }
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject reader = new JSONObject(result);

                answerT.setText(reader.getString("answerT"));
                count.setText(reader.getString("count"));

                Float flAnsw = Float.parseFloat(reader.getString("answerT"));
                Float flcount = Float.parseFloat(reader.getString("count"));
                int percent = Float.valueOf((flAnsw / flcount)*100).intValue();

                procent.setText(Integer.toString(percent)+"%");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));

            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();

            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";

        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }

        return result;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }
}
