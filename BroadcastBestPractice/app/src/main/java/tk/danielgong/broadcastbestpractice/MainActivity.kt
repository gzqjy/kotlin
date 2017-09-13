package tk.danielgong.broadcastbestpractice

import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        force_offline.onClick {
            val intent = Intent("tk.danielgong.broadcastbestpractice.FORCE_OFFLINE")
            sendBroadcast(intent)
        }
    }
}
