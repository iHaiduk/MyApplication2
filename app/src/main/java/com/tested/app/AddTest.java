package com.tested.app;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AddTest extends Activity {
    public RadioGroup rg;
    public EditText answer1, answer2, answer3, answer4, textQuestion;
    private Integer id_question;
    private Boolean over = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_test);

        rg = (RadioGroup) findViewById(R.id.choseQuestion);
        answer1 = (EditText) findViewById(R.id.answer1);
        answer2 = (EditText) findViewById(R.id.answer2);
        answer3 = (EditText) findViewById(R.id.answer3);
        answer4 = (EditText) findViewById(R.id.answer4);
        textQuestion = (EditText) findViewById(R.id.textQuestion);
        id_question = Integer.parseInt(getIntent().getStringExtra("id"));
    }

    public void addQuestion(View v){
        new HttpAsyncTask().execute();
        Button addTestButton = (Button) findViewById(R.id.addTestButton);
        addTestButton.setEnabled(false);
    }
    public void overQuestion(View v){
        Intent refresh = new Intent(this, AddPanel.class);
        startActivity(refresh);
        finish();
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            return GET();
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject reader = new JSONObject(result);
                Boolean result_http = reader.getBoolean("result");
                if (result_http){
                        Intent refresh = new Intent(AddTest.this, AddTest.class);
                        refresh.putExtra("id", id_question.toString());
                        startActivity(refresh);
                        finish();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public String GET(){
        InputStream inputStream = null;
        String result = "";
        try {

            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://kursova.esy.es/question/create");

            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            RadioButton radiovalue = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
            nameValuePairs.add(new BasicNameValuePair("answer1", answer1.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("answer2", answer2.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("answer3", answer3.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("answer4", answer4.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("id_question", id_question.toString()));
            nameValuePairs.add(new BasicNameValuePair("textQuestion", textQuestion.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("value", radiovalue.getText().toString()));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(httppost);

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
