package com.tested.app;

import android.app.Activity;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.tested.util.DBHelper;
import com.tested.util.RequestCode;

import java.util.ArrayList;
import java.util.Arrays;


public class MainActivity extends Activity {

    private DBHelper mydb ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }



    public void onShow(View v){
        Intent intent;
        mydb = new DBHelper(this);
        Log.i("DEV", mydb.getDatabaseName());
        switch (v.getId()){
            case R.id.admin:
                intent = new Intent(this, AdminActivity.class);
                startActivityForResult(intent, RequestCode.REQ_CODE_ADMIN);
                break;
            case R.id.test:
                intent = new Intent(this, TesterActivity.class);
                startActivityForResult(intent, RequestCode.REQ_CODE_TESTER);
                break;
        }
    }
}
