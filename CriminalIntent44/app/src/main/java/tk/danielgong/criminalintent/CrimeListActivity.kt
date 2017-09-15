package tk.danielgong.criminalintent

import android.support.v4.app.Fragment

/*
 * Created by gongzhq on 2017/9/14.
 */

class CrimeListActivity: SingleFragmentActivity() {
    override fun createFragment(): Fragment {
        return CrimeListFragment()
    }
}