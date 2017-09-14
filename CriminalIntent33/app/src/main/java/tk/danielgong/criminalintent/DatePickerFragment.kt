package tk.danielgong.criminalintent

import android.app.Dialog
import android.content.Intent
import android.support.v4.app.DialogFragment
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.dialog_date.*
import java.util.*
import android.app.Activity

/**
 * Created by gongzhq on 2017/9/14.
 */

class DatePickerFragment: DialogFragment() {

    companion object {
        public val EXTRA_DATE = "com.bignerdranch.android.criminalintent.date"
        private val ARG_DATE = "date"
        public fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            val fragment = DatePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.setTime(date)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_date, null)
        dialog_date_date_picker.init(year, month, day, null)
        return AlertDialog.Builder(activity)
                .setView(view)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok) { dialog, which ->
                    val year = dialog_date_date_picker.year
                    val month = dialog_date_date_picker.month
                    val day = dialog_date_date_picker.dayOfMonth
                    val date = GregorianCalendar(year, month, day).time
                    sendResult(Activity.RESULT_OK, date)
                }
                .create()
    }

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment == null) {
            return
        }
        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)
        targetFragment.onActivityResult(targetRequestCode, resultCode, intent);
    }
}