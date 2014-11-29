package com.tested.app;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Ihor on 30.11.2014.
 */
public class AdminViewTest extends Activity {
    private boolean admin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_test);
        if(Integer.parseInt(getIntent().getStringExtra("admin"))==1)
            admin = true;

        System.out.println(admin);
    }
}
