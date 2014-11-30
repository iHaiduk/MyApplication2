package com.tested.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UserViewTest extends Activity {

    private int viewQuestion = 0;
    private JSONArray reader;
    private JSONObject resultObj;
    private String result;
    private Button nexQuest;

    private TextView textQuestion_user;
    private TextView answer1_user;
    private TextView answer2_user;
    private TextView answer3_user;
    private TextView answer4_user;
    private TextView nameTest;

    public RadioGroup rg;
    private String uid_test;

    private String answer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_row_question);

        result = getIntent().getStringExtra("result");
        try {
            resultObj = new JSONObject(result);
            reader = resultObj.getJSONArray("question");

            nexQuest = (Button) findViewById(R.id.nextQuestion);

            textQuestion_user = (TextView) findViewById(R.id.textQuestion_user);
            answer1_user = (TextView) findViewById(R.id.answer1_user);
            answer2_user = (TextView) findViewById(R.id.answer2_user);
            answer3_user = (TextView) findViewById(R.id.answer3_user);
            answer4_user = (TextView) findViewById(R.id.answer4_user);
            nameTest = (TextView) findViewById(R.id.nameTest);

            rg = (RadioGroup) findViewById(R.id.choseQuestion_user);

            nexQuest.setEnabled(true);

            nameTest.setText(resultObj.getString("name"));

            if(reader.length() > 0 && viewQuestion != -1){
                nexQuest.setEnabled(true);
                setQuestion();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void nextQuestion(View v) throws JSONException {
        if(viewQuestion != -1)
            viewQuestion++;
        if(reader.length() > viewQuestion && viewQuestion != -1){
            if(rg.getCheckedRadioButtonId() != -1) {
                RadioButton radiovalue = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                if(radiovalue.isChecked())
                    answer += radiovalue.getText().toString()+",";
                else
                    answer += ",";
                rg.clearCheck();
            }
            nexQuest.setEnabled(true);
            setQuestion();
        }else if(viewQuestion != -1){
            if(rg.getCheckedRadioButtonId() != -1) {
                RadioButton radiovalue = (RadioButton) findViewById(rg.getCheckedRadioButtonId());
                if(radiovalue.isChecked())
                    answer += radiovalue.getText().toString()+",";
                else
                    answer += ",";
                rg.clearCheck();
            }
            nexQuest.setText("Результат");
            viewQuestion = -1;
        }else{
            System.out.println(answer);
            Intent intent = new Intent(UserViewTest.this, Result.class);
            intent.putExtra("answer", answer.toString());
            intent.putExtra("uid_test", uid_test.toString());
            startActivity(intent);
            finish();
        }
    }

    public void setQuestion() throws JSONException {
        JSONObject object = reader.getJSONObject(viewQuestion);

        uid_test = object.getString("uid_test");

        textQuestion_user.setText(object.getString("question"));
        answer1_user.setText(object.getString("answ1"));
        answer2_user.setText(object.getString("answ2"));
        answer3_user.setText(object.getString("answ3"));
        answer4_user.setText(object.getString("answ4"));
        System.out.println(object);
    }
    public void backToList(View v){
        Intent intent = new Intent(this, testListView.class);
        startActivity(intent);
        finish();
    }
}
