package com.example.exo101

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat

class AwesomeForegroundService: Service() {

    val TAG = "STICKY_SERVICE"

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "start service...")
        start(startId)
        // Do something
        return START_STICKY
    }

    override fun onDestroy() {
        Log.d(TAG, "service destroy...")
        super.onDestroy()
    }

    private fun start(id: Int) {
        val notification = NotificationCompat.Builder(this, "file_downloader")
                .setContentTitle("Download in progress")
                .setContentText("Progress : 50%")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Download started")
                .build()
        startForeground(id, notification)
    }
}