package com.tested.app;

import android.app.Activity;
import android.os.Bundle;

public class AdminViewTest extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_row_question_amin);

        System.out.println(getIntent().getStringExtra("result"));
    }
}
