package id.wesudgitgud.burrows.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by rezaramadhan on 24/02/2017.
 */

public class CounterTimeService extends Service {
    private String TAG = "CounterTimeService";

    public int secTimeElapsed;
    private long startTime;
    private long endTime;

    @Override
    public void onCreate() {
        super.onCreate();
        secTimeElapsed = 0;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startTime = System.currentTimeMillis();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        endTime = System.currentTimeMillis();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
