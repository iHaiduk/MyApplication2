package com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Ihor on 23.11.2014.
 */
public class LastActivity extends Activity {

    private TextView txtEmail;
    private TextView txtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.last);

        txtEmail = (TextView) findViewById(R.id.email_view);
        txtPass = (TextView) findViewById(R.id.pass_view);

        txtEmail.setText(getIntent().getStringExtra("email"));
        txtPass.setText(getIntent().getStringExtra("password"));
    }
    public void goToBackActivity(View view){

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
