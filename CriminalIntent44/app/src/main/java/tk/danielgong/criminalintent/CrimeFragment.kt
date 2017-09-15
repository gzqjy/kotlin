package tk.danielgong.criminalintent

import android.app.Activity
import android.text.format.DateFormat
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import kotlinx.android.synthetic.main.fragment_crime.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import java.util.*

/*
 * Created by Administrator on 2017/9/10.
 */

class CrimeFragment: Fragment() {
    companion object {
        private val ARG_CRIME_ID = "crime_id"
        private val DIALOG_DATE = "DialogDate"
        private val DIALOG_TIME = "DialogTIME"
        private val REQUEST_DATE = 0
        private val REQUEST_TIME = 1
        fun newInstance(crimeId: UUID): CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeId)

            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }
    private var mCrime: Crime ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        val crimeId = arguments.getSerializable(ARG_CRIME_ID) as UUID
        mCrime = CrimeLab.getCrime(crimeId)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_crime, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        crime_title.setText( mCrime?.mTitle)
        crime_title.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mCrime?.mTitle = p0.toString()
//                updateCrime()
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        crime_date.onClick {
            val dialog = DatePickerFragment.newInstance(mCrime!!.mDate)
            dialog.setTargetFragment(this@CrimeFragment, REQUEST_DATE)
            dialog.show(fragmentManager, DIALOG_DATE)
        }
        crime_times.onClick {
            val dialog = TimePickerFragment.newInstance(mCrime!!.mDate)
            dialog.setTargetFragment(this@CrimeFragment, REQUEST_TIME)
            dialog.show(fragmentManager, DIALOG_TIME)
        }
        updateDate()

        crime_solved.isChecked = mCrime!!.mSolved
        crime_solved.onClick {
            mCrime?.mSolved = crime_solved.isChecked
        }
    }

//    private fun getResult() {
//        activity.setResult(Activity.RESULT_OK, null)
//    }

    private fun updateDate()
    {
        val d = mCrime?.mDate
        if (crime_date != null) {
            val c = DateFormat.format("EEEE, MMM dd, yyyy", d)
            crime_date.text = c
        }
        if (crime_times != null) {
            val t = DateFormat.format("h:mm a", d)
            crime_times.text = t
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( resultCode != Activity.RESULT_OK) {
            return
        }
        if (requestCode == REQUEST_DATE) {
            val date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
            mCrime?.mDate = date
            updateDate()
        }
        if (requestCode == REQUEST_TIME) {
            val date = data?.getSerializableExtra(TimePickerFragment.EXTRA_TIME) as Date
            mCrime?.mDate = date
            updateDate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_crime, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_item_remove_crime -> {
                CrimeLab.removeCrime(mCrime!!)
                activity.onBackPressed()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}