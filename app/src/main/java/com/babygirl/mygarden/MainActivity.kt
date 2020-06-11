package com.babygirl.mygarden

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.Build
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            entergarden()
        }
        createNotificationChannel()

        val notifyIntent = Intent(this, MyReceiver::class.java)

        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8 )
            set(Calendar.MINUTE, 0 )
            set(Calendar.SECOND, 0 )
            set(Calendar.MILLISECOND, 0)
        }

        var alarmMgr = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val alarmIntent = Intent(this, Page2::class.java).let { intent ->
            PendingIntent.getBroadcast(this, 0, notifyIntent, 0)
        }


        alarmMgr?.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            alarmIntent
        )
    }
        fun entergarden() {
        val intent = Intent(this, Page2::class.java)
        startActivity(intent)}

             fun createNotificationChannel() {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val name = getString(R.string.channel_name)
                    val descriptionText = getString(R.string.channel_description)
                    val importance = NotificationManager.IMPORTANCE_DEFAULT
                    val channel = NotificationChannel("14", name, importance).apply {
                        description = descriptionText
                    }
                    // Register the channel with the system
                    val notificationManager: NotificationManager =
                        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                    notificationManager.createNotificationChannel(channel)
                }
            }


    }

