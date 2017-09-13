package tk.danielgong.broadcastbestpractice

import android.content.*
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log

/**
 * Created by gongzhq on 2017/9/13.
 */

open class BaseActivity: AppCompatActivity() {
    private var receiver: ForceOfflineReceiver ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("tk.danielgong.broadcastbestpractice.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        if (receiver != null) {
            unregisterReceiver(receiver)
            receiver = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    class ForceOfflineReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Log.d("DDD", "eee")

            val builder = AlertDialog.Builder(p0!!)
            builder.setTitle("Warning")
            builder.setMessage("You are forced to be offline. Please try to login again")
            builder.setCancelable(false)
            builder.setPositiveButton("OK", DialogInterface.OnClickListener {
                dialogInterface, i -> {
                    ActivityCollector.finishAll()
                    val intent = Intent(p0, LoginActivity::class.java)
                    p0?.startActivity(intent)
                }
            })
            builder.show()
        }
    }
}