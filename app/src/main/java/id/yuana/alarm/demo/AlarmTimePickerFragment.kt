package id.yuana.alarm.demo

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.widget.TimePicker
import java.util.*

class AlarmTimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    private lateinit var listener: AlarmActivity

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) = listener.initAlarm(hourOfDay, minute)

    override fun onAttach(context: Context?) {
        super.onAttach(context)

        if (context is AlarmActivity) {
            listener = context
        }
    }

    companion object {
        const val TAG: String = "AlarmTimePickerFragment"

        fun newInstance(): AlarmTimePickerFragment = AlarmTimePickerFragment()
    }
}
