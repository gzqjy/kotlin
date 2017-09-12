package tk.danielgong.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v4.app.FragmentManager
import kotlinx.android.synthetic.main.fragment_crime.*

class CrimeActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)
        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            val frag = CrimeFragment()
            supportFragmentManager.beginTransaction()
                .add(R.id.fragment_container, frag)
                .commit()
        }
    }
}
