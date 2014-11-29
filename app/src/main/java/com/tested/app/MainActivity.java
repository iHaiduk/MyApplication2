package com.tested.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.tested.util.RequestCode;

public class MainActivity extends Activity {
    private static final int TIME_INTERVAL = 3000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
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
                intent = new Intent(this, testListView.class);
                startActivityForResult(intent, RequestCode.REQ_CODE_TESTER);
                break;
        }
    }

    @Override
    public void onBackPressed()
    {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis())
        {
            super.onBackPressed();
            return;
        }
        else {
            Toast toast = Toast.makeText(this, "Нажмите еще раз кнопку 'назад' для выхода", Toast.LENGTH_SHORT);
            TextView v = (TextView) toast.getView().findViewById(android.R.id.message);
            if( v != null) v.setGravity(Gravity.CENTER);
            toast.show();
        }

        mBackPressed = System.currentTimeMillis();
    }
}
