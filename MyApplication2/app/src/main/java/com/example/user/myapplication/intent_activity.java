package com.example.user.myapplication;

import android.content.Intent;
import android.content.IntentSender;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class intent_activity extends AppCompatActivity {
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_activity);

        tv = (TextView)findViewById(R.id.tV);

        Intent iT = this.getIntent(); // 메인에서 보내주는 메세지를 getIntent로 받음.
        String str = iT.getStringExtra("time"); //메인에서 time이라는 key로 보내주어서 time으로 찾아 String 변수에 넣어줌.
        Log.d("gggggggg",str);

        tv.setText(str);




    }
}
