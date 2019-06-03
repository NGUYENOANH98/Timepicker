package com.nguyenoanh.timer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Đang trong Receiver", "Xin Chao!!!");

        Intent myIntent = new Intent(context, Music.class);

        context.startService(myIntent);
    }
}
