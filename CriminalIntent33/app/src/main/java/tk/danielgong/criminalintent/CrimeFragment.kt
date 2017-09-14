package tk.danielgong.criminalintent

import android.app.Activity
import android.app.DialogFragment
import android.content.Intent
import android.support.v4.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast
import java.util.*

/**
 * Created by Administrator on 2017/9/10.
 */

class CrimeFragment: Fragment() {
    companion object {
        private val ARG_CRIME_ID = "crime_id"
        private val DIALOG_DATE = "DialogDate"
        private val REQUEST_DATE = 0
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
//        mCrime = Crime()
        val crimeId = arguments.getSerializable(ARG_CRIME_ID) as UUID
        mCrime = CrimeLab.getCrime(crimeId)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater?.inflate(R.layout.fragment_crime, container, false)
        return v
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        crime_title.setText(mCrime?.mTitle)
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

        crime_date.text  = mCrime?.mDate.toString()

        crime_date.onClick {
            val dialog = DatePickerFragment.newInstance(mCrime!!.mDate)
            var fm = fragmentManager
            dialog.setTargetFragment(this@CrimeFragment, REQUEST_DATE)
            dialog.show(fragmentManager, DIALOG_DATE)
        }

//        if (mCrime != null) {
        crime_solved.isChecked = mCrime!!.mSolved
//        }
        crime_solved.onClick {
            mCrime?.mSolved = crime_solved.isChecked
        }
    }

    fun getResult() {
        activity.setResult(Activity.RESULT_OK, null)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ( resultCode != Activity.RESULT_OK) {
            return
        }
        if (resultCode == REQUEST_DATE) {
            val date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
            mCrime?.mDate = date
            crime_date.text = mCrime?.mDate.toString()

        }
    }
}