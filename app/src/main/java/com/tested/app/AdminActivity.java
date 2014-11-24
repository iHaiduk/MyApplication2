package com.tested.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * Created by Ihor on 24.11.2014.
 */
public class AdminActivity  extends Activity {

    private EditText name;
    private EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_auth);

        name = (EditText) findViewById(R.id.name);
        pass = (EditText) findViewById(R.id.pass);
    }

    public void onPresented(View v){
        Intent intent = new Intent();
        intent.putExtra("name", name.getText().toString());
        intent.putExtra("pass", pass.getText().toString());
        setResult(RESULT_OK);
        finish();
    }

    public void onBack(View v){
        setResult(RESULT_OK);
        finish();
    }
}
