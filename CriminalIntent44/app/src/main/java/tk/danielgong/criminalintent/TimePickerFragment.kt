package tk.danielgong.criminalintent

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.dialog_time.*
import kotlinx.android.synthetic.main.dialog_time.view.*
import java.util.*

/*
 * Created by gongzhq on 2017/9/15.
 */

class TimePickerFragment: DialogFragment() {
    companion object {
        val EXTRA_TIME = "tk.danielgong.criminalintent.time"
        private val ARG_TIME = "time"
        fun newInstance(date: Date): TimePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_TIME, date)
            val fragment = TimePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments.getSerializable(ARG_TIME) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_time, dialog_time_layout)
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            view.dialog_time_time_picker.currentHour = hour
            view.dialog_time_time_picker.currentMinute = minute
        } else {
            view.dialog_time_time_picker.hour = hour
            view.dialog_time_time_picker.minute = minute
        }
        return AlertDialog.Builder(activity)
                .setView(view)
                .setTitle(R.string.time_picker_title)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    val year = calendar.get(Calendar.YEAR)
                    val month = calendar.get(Calendar.MONTH)
                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                    var hours = 0
                    var minutes = 0
                    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                        hours = view.dialog_time_time_picker.currentHour
                        minutes = view.dialog_time_time_picker.currentMinute
                    } else {
                        hours = view.dialog_time_time_picker.hour
                        minutes = view.dialog_time_time_picker.minute
                    }
                    val dates = GregorianCalendar (year, month, day, hours, minutes).time
                    sendResult(Activity.RESULT_OK, dates)
                }
                .create()
    }

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment == null) {
            return
        }
        val intent = Intent()
        intent.putExtra(EXTRA_TIME, date)
        targetFragment.onActivityResult(targetRequestCode, resultCode, intent)
    }
}