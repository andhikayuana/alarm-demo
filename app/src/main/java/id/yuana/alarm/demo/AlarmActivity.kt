package id.yuana.alarm.demo

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_alarm.*
import java.util.*


class AlarmActivity : AppCompatActivity() {

    private var withVibrate: Boolean = false
    private var withSound: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)
        initView()
    }

    private fun initView() {
        btnTimePicker.setOnClickListener {
            val alarmTimePickerFragment = AlarmTimePickerFragment.newInstance()
            alarmTimePickerFragment.show(supportFragmentManager, AlarmTimePickerFragment.TAG)
        }
        swVibrate.setOnCheckedChangeListener { buttonView, isChecked ->
            withVibrate = isChecked
        }

        swSound.setOnCheckedChangeListener { buttonView, isChecked ->
            withSound = isChecked
        }
    }

    fun initAlarm(hourOfDay: Int, minute: Int) {
        val alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(this, AlarmReceiver::class.java)
        intent.putExtra("EXTRA_HOUR_OF_DAY", hourOfDay)
        intent.putExtra("EXTRA_MINUTE", minute)
        intent.putExtra("EXTRA_WITH_SOUND", withSound)
        intent.putExtra("EXTRA_WITH_VIBRATE", withVibrate)
        intent.putExtra("EXTRA_WHAT_THIS", "ANU")

        val alarmIntent = PendingIntent.getBroadcast(this, 0, intent, 0)

        // Set the alarm to start at approximately match with your picker
        val calendar = Calendar.getInstance()
        calendar.setTimeInMillis(System.currentTimeMillis())
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
        calendar.set(Calendar.MINUTE, minute)
        calendar.set(Calendar.SECOND, 0)

        // With setInexactRepeating(), you have to use one of the AlarmManager interval
        // constants--in this case, AlarmManager.INTERVAL_DAY.
        alarmMgr.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, alarmIntent)
    }
}
