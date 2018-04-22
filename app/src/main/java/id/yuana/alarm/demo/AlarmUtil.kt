package id.yuana.alarm.demo

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.media.RingtoneManager




class AlarmUtil {

    companion object {

        @SuppressLint("WrongConstant")
        fun showNotification(context: Context, withSound: Boolean, withVibrate: Boolean) {

            val intent = Intent(context, AlarmActivity::class.java)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

            val mBuilder = NotificationCompat.Builder(context, "1")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("My notification")
                    .setContentText("Much longer text that cannot fit one line...")
                    .setStyle(NotificationCompat.BigTextStyle()
                            .bigText("Much longer text that cannot fit one line..."))
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)

            if (withSound) {
                val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                mBuilder.setSound(alarmSound)
            }

            if (withVibrate) {
                val vibrate = longArrayOf(0, 200, 0, 200)
                mBuilder.setVibrate(vibrate)
            }

            val notificationManager = context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // Create the NotificationChannel, but only on API 26+ because
                // the NotificationChannel class is new and not in the support library
                val name = "channel name"
                val description = "channel desc"
                val importance = NotificationManagerCompat.IMPORTANCE_DEFAULT
                val channel = NotificationChannel("1", name, importance)
                channel.description = description
                // Register the channel with the system
                notificationManager.createNotificationChannel(channel)
            }

            // notificationId is a unique int for each notification that you must define
            notificationManager.notify(1, mBuilder.build())
        }
    }
}