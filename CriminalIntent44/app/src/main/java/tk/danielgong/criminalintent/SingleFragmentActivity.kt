package tk.danielgong.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/*
 * Created by gongzhq on 2017/9/14.
 */

abstract class SingleFragmentActivity: AppCompatActivity() {
    protected abstract fun createFragment(): Fragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (fragment == null) {
            val frag = createFragment()
            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, frag)
                    .commit()
        }
    }
}