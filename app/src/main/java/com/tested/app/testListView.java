package com.tested.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.tested.adapter.CustomListViewAdapter;
import com.tested.model.TestModel;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class testListView extends Activity {
    private ListView listView;
    private int typeSend = 1;
    private String admin = "0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tester);
        listView = (ListView) findViewById(R.id.listView);
        if(getIntent().hasExtra("admin") && Integer.parseInt(getIntent().getStringExtra("admin"))==1 )
            admin = "1";

        new HttpAsyncTask().execute("http://kursova.esy.es/test/all");
    }


    private List<TestModel> initData(JSONArray array) throws JSONException {

        List<TestModel> list = new ArrayList<TestModel>();

        for(int j=0; j<array.length();j++)
        {
            JSONObject curr = array.getJSONObject(j);
            if(curr.getInt("count") > 0)
                list.add(new TestModel(curr.getString("id"), curr.getString("name"), curr.getInt("count")));
        }

        return list;
    }

    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {

                switch(typeSend) {
                    case 1:
                        try {
                            JSONArray reader = new JSONArray(result);
                            CustomListViewAdapter adapter = new CustomListViewAdapter(testListView.this, initData(reader));
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    TextView textViewItem = ((TextView) view.findViewById(R.id.idRow));
                                    typeSend = 2;
                                    if(admin == "0")
                                        new HttpAsyncTask().execute("http://kursova.esy.es/question/view/id/" + textViewItem.getText().toString());
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 2:
                        Intent intent = new Intent(testListView.this, UserViewTest.class);

                        Log.e("DEV", admin);
                        intent.putExtra("result",result);
                        startActivity(intent);
                        finish();
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
