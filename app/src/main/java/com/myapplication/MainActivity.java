package com.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText email;
    private EditText password;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) findViewById(R.id.myemail);
        password = (EditText) findViewById(R.id.password);
    }

    public void senData(View view){
        Intent intent = new Intent(this, LastActivity.class);
        intent.putExtra("email", email.getText().toString());
        intent.putExtra("password", password.getText().toString());
        startActivity(intent);
    }

    public void onShow(View view){
        Toast toast = Toast.makeText(getApplicationContext(), "Hello", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.TOP,0,0);
        toast.show();
    }
    public void onPosition(View view){
        Context context = getApplicationContext();
        Configuration configuration = getResources().getConfiguration();

        if(configuration.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(context, "Портрет", Toast.LENGTH_SHORT).show();
        }
        if(configuration.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(context, "Альбом", Toast.LENGTH_SHORT).show();
        }
    }
}
