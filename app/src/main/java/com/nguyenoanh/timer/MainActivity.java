package com.nguyenoanh.timer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button btnHenGio;
    Button btnDung;

    TextView txtHienThi;
    TimePicker timePicker;
    //Lớp trừu tượng cung cấp pt chuyển đổi ngày giờ
    Calendar calendar;

    //Cho phép truy cập vào hệ thống báo động của máy
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnHenGio = (Button) findViewById(R.id.btnHenGio);
        btnDung = (Button) findViewById(R.id.btnDung);

        txtHienThi = (TextView) findViewById(R.id.txtHienThi);
        timePicker = (TimePicker) findViewById(R.id.TimePicker);
        calendar = Calendar.getInstance();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        //Truyền dữ liệu từ MainActivity sang Alarm_Server
        final Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        btnHenGio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getCurrentHour());
                calendar.set(Calendar.MINUTE, timePicker.getCurrentMinute());

                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();

                String strHour = String.valueOf(hour);
                String strMinute = String.valueOf(minute);

                if( hour > 12){
                    strHour = String.valueOf(hour - 12);
                }
                if( minute < 10){
                    strMinute = "0" + String.valueOf(minute);
                }

                pendingIntent = PendingIntent.getBroadcast(
                        MainActivity.this,0, intent, PendingIntent.FLAG_UPDATE_CURRENT
                );
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                txtHienThi.setText("Giờ bạn đã đặt: "+strHour +":"+strMinute);
            }
        });

        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("extra", "no");
                sendBroadcast(intent);
                alarmManager.cancel(pendingIntent);
                txtHienThi.setText("Đã dừng");
                pendingIntent.cancel();
            }
        });
    }
}

