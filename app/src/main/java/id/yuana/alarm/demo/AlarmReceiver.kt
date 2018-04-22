package id.yuana.alarm.demo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive : i am here")
        Log.d(TAG, "hourOfDay : " + intent!!.extras["EXTRA_HOUR_OF_DAY"])
        Log.d(TAG, "minute : " + intent.extras["EXTRA_MINUTE"])
        Log.d(TAG, "withSound : " + intent.extras["EXTRA_WITH_SOUND"].toString())
        Log.d(TAG, "withVibrate : " + intent.extras["EXTRA_WITH_VIBRATE"].toString())

        AlarmUtil.showNotification(
                context!!,
                intent.extras["EXTRA_WITH_SOUND"] as Boolean,
                intent.extras["EXTRA_WITH_VIBRATE"] as Boolean
        )
    }

    companion object {
        const val TAG: String = "AlarmReceiver"
    }
}