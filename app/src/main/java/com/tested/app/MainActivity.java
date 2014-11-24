package com.tested.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.tested.util.RequestCode;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onShow(View v){
        Intent intent;
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
