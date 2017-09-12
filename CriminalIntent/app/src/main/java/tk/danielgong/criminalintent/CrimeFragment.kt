package tk.danielgong.criminalintent

import android.support.v4.app.Fragment
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import kotlinx.android.synthetic.main.fragment_crime.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.toast

/**
 * Created by Administrator on 2017/9/10.
 */

class CrimeFragment: Fragment() {
    var mCrime = Crime()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val v = inflater?.inflate(R.layout.fragment_crime, container, false)
        return v!!
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//    }
        Log.d("GGGKKK0", view?.toString())
        Log.d("GGGKKK0", "====")

        crime_title?.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mCrime.mTitle = p0.toString()
                Log.d("GGGooo:", p0.toString())
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
        val btn = view?.findViewById<Button>(R.id.crime_date)
//        btn?.text = mCrime.mDate.toString()
        Log.d("GGG0:", btn?.toString())

//        Log.d("GGG1:", crime_date?.toString())

//        this.crime_date?.text = "asd"
        if (crime_date != null) {
            val btn = crime_date as Button
            crime_date.text  = mCrime.mDate.toString()
            Log.d("GGG", mCrime.mDate.toString())
        }
        crime_date?.onClick {
            toast(mCrime.mDate.toString())
        }
//        crime_data?.isEnabled = true
        crime_solved?.isChecked = true
        crime_solved?.onClick {
            mCrime.mSolved = crime_solved.isChecked
        }
//        return v!!
    }
}