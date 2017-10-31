package com.example.user.myapplication;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.sql.Time;

public class alarm extends AppCompatActivity {
    AlarmManager alarmManager; //내가 지정한 시간에 실행할 것을 receiver에게 알려줌
    Intent intent;
    PendingIntent pendingIntent;
    Calendar calendar;
    Button reg;
    Button unreg;
    //ToggleButton tbt;
    TimePicker tp;
    int hour;
    int minute;
    int second=0;
    final String STR = "com.example.uesr.myapplication.alarm";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        reg = (Button)findViewById(R.id.bt);
        unreg = (Button)findViewById(R.id.bt2);
        // tbt = (ToggleButton)findViewById(R.id.tBt);
        tp = (TimePicker)findViewById(R.id.tP);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRegister();
            }
        });

        unreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unRegister();
            }
        });
    }
    void onRegister() {
        hour = tp.getCurrentHour(); //timePicker의 시간을 받음
        minute = tp.getCurrentMinute();//timePicker의 분을 받음
        second = 0;
        //Toast.makeText(alarm.this, "" + hour + " : " + minute, Toast.LENGTH_LONG).show(); //""를 추가함으로써 int형 변수를 String으로 type cast
        /*Intent it = new Intent(alarm.this, intent_activity.class); //intent 함수로 main에서 intent_activity 편지를 보낸다.
        it.putExtra("time", ""+ hour + " : " + minute); //메세지 저장, time은 인덱스라 생각하자, ""를 추가함으로써 int형 변수를 String으로 type cast
        startActivity(it); //액티비티를 깨움
        */
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE); //객체 생성
        intent = new Intent(STR); // 사용자 정의 Action Intent
        pendingIntent = PendingIntent.getBroadcast(alarm.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT); //pending intent 객체 생성(제3자에게 부탁)

        calendar = Calendar.getInstance(); // calendar 객체 생성
        calendar.set(Calendar.HOUR_OF_DAY, hour); //내가 입력한 시간 저장
        calendar.set(Calendar.MINUTE, minute);// 입력한 분 저장
        calendar.set(Calendar.SECOND, second);// 입력한 초 저장
        Log.d("test","test");
        if (Build.VERSION.SDK_INT >= 23) {// 상위버전호환을 위한 조건
            //RTC_WAKEUP 시간의 기준을 정함.
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            //정확한시간을 맞추기위해 버전이 상승되며 set이 달라짐
            //alarmManager에 세팅. receiver에게 전달하기 위한 세팅
        }
    }

    void unRegister(){
        intent = new Intent(STR);
        Intent alarmToService = new Intent(alarm.this, AlarmService.class);
        pendingIntent = PendingIntent.getBroadcast(
                alarm.this,
                0,
                intent,PendingIntent.FLAG_UPDATE_CURRENT
        );
        alarmManager.cancel(pendingIntent);
        alarmToService.putExtra("cancle",true);
        startService(alarmToService);
    }
}
