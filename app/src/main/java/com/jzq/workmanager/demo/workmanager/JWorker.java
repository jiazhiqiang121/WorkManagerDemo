package com.jzq.workmanager.demo.workmanager;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.jzq.workmanager.demo.alarmmanager.ClockAlarmActivity;

import androidx.work.Worker;

/**
 * author：ZhiQiangJia
 * create time: 2018/6/30 19:05
 * description:
 */
public class JWorker extends Worker {
    @NonNull
    @Override
    public Result doWork() {
        Intent clockIntent = new Intent(getApplicationContext(), ClockAlarmActivity.class);
        clockIntent.putExtra("msg", "闹钟响了");
        clockIntent.putExtra("flag", 1);
        clockIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplicationContext().startActivity(clockIntent);
        return Result.SUCCESS;
    }
}
