package com.example.user.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;

import java.net.URI;

import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

import static android.media.RingtoneManager.getActualDefaultRingtoneUri;

// 서비스를 실행시키는 방법 2가지 : Bind로 실행, startService(intent)로 실행
public class AlarmService extends Service {
    final String TAG = "zbg";
    Ringtone ringTone;
    MediaPlayer mp;

    public AlarmService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // 생성해주는 놈, 최초생성시 사용
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "Service onCreate");//로그를 남김
    }

    @Override // 서비스를 실행시키는 방법, Bind를 사용하지않고 실행
    // 이 서비스가 종료되지 않는 한 계속 사용, 생성 이후부터는 다 이것을 사용, 서비스의 행동 저장.
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean isCancle;

        Log.d(TAG, "Service Start Command");
        /* content provider의 한종류 안드로이드의 리소스에 접근 */
        Uri uri = RingtoneManager.getActualDefaultRingtoneUri(
                getApplicationContext(),
                RingtoneManager.TYPE_RINGTONE
        );
        if (intent != null) {
            if (!intent.getBooleanExtra("cancle", false)) {
                /*if (ringTone == null)
                    ringTone = RingtoneManager.getRingtone(getApplicationContext(), uri);

                if (ringTone != null) {
                    ringTone.setStreamType(AudioManager.STREAM_ALARM);
                    ringTone.play();
                }*/
                mp = MediaPlayer.create(this,R.raw.zia);
                mp.start();
                //return START_STICKKY. 꺼지지 않게 함 꺼져도 좀비처럼 살아남.
                //return STRAT_NOT_STICKY. 한번 종료하면 살아나지 않음.
            } else {
               // ringTone.stop();
                mp.stop();
            }
        }
        return super.onStartCommand(intent, flags, startId);


    }
}
