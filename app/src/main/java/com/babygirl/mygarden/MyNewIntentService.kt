package com.babygirl.mygarden

import android.app.IntentService
import android.app.PendingIntent
import android.content.Intent
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MyNewIntentService: IntentService("MyNewIntentService") {
    override fun onHandleIntent(intent: Intent) {
        val intent = Intent(this, Page2::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
        var builder = NotificationCompat.Builder(this, "14")
            .setSmallIcon(R.drawable.notif)
            .setContentTitle("Reminder")
            .setContentText("Time to check on your plants!")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        with(NotificationManagerCompat.from(this)) {
            notify(1, builder.build())
        }

    }
}