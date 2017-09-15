package tk.danielgong.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_crime_pager.*
import java.util.*

/*
 * Created by gongzhq on 2017/9/14.
 */

class CrimePagerActivity: AppCompatActivity() {
    companion object {
        private val EXTRA_CRIME_ID = "tk.danielgong.criminalintent.crime_id"
        fun newIntent(packageContext: Context, crimeId: UUID): Intent {
            val intent = Intent(packageContext, CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }
    private var mCrimes: MutableList<Crime> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)
        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID

        mCrimes = CrimeLab.crimes
        activity_crime_pager_view_pager.adapter = object:FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val crime = mCrimes!![position]
                return CrimeFragment.newInstance(crime.mId)
            }
            override fun getCount(): Int {
                return mCrimes!!.size
            }
        }

        val index = mCrimes?.indexOfFirst { it.mId == crimeId }
        if (index != null)
            activity_crime_pager_view_pager.currentItem = index
    }
}