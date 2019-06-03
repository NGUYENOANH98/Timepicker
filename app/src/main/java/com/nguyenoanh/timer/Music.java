package com.nguyenoanh.timer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

public class Music extends Service {
    MediaPlayer mediaPlayer;

    private boolean isRunning;
    private int startId;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("Đang trong Music", "Xin Chào (^.^)");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        mediaPlayer = MediaPlayer.create(this, R.raw.ring1);
        mediaPlayer.start();

        return START_NOT_STICKY;
    }

}
