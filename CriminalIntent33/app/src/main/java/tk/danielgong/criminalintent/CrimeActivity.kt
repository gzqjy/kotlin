package tk.danielgong.criminalintent

import android.support.v4.app.Fragment
import java.util.*

/**
 * Created by gongzhq on 2017/9/14.
 */

class CrimeActivity: SingleFragmentActivity() {
    private val EXTRA_CRIME_ID = "tk.danielgong.criminalintent.crime_id"
    override fun createFragment(): Fragment {
        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
        return CrimeFragment.newInstance(crimeId)
    }
}