package tk.danielgong.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.LocalBroadcastManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class MainActivity : AppCompatActivity() {
    private var networkChangeReceiver:NetworkChangeReceiver? = null
    private var localReceiver:LocalReceiver ?= null
    private var localBroadcastManager:LocalBroadcastManager ?= null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        localBroadcastManager = LocalBroadcastManager.getInstance(this)

        button.onClick {
            val intent = Intent("tk.danielgong.broadcasttest.MY_BROADCAST")
            sendBroadcast(intent)
//            sendOrderedBroadcast(intent, null)
        }
        local_button.onClick {
            val intent = Intent("tk.danielgong.broadcasttest.LOCAL_BROADCAST")
            localBroadcastManager?.sendBroadcast(intent)
//            localBroadcastManager?.sendBroadcastSync(intent)
        }
        val chage_intentFilter = IntentFilter()
        chage_intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE")
        networkChangeReceiver = NetworkChangeReceiver()
        registerReceiver(networkChangeReceiver, chage_intentFilter)

        val intentFilter = IntentFilter()
        intentFilter.addAction("tk.danielgong.broadcasttest.LOCAL_BROADCAST")
        networkChangeReceiver = NetworkChangeReceiver()
        localReceiver = LocalReceiver()
        localBroadcastManager?.registerReceiver(localReceiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(networkChangeReceiver)
        localBroadcastManager?.unregisterReceiver(localReceiver)
    }

    class LocalReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            Toast.makeText(p0, "receiver local broadcast", Toast.LENGTH_SHORT).show()
        }
    }

    class NetworkChangeReceiver: BroadcastReceiver() {
        override fun onReceive(p0: Context?, p1: Intent?) {
            val connectionManager = p0?.getSystemService(Context.CONNECTIVITY_SERVICE)
            val networkInfo = (connectionManager as ConnectivityManager).activeNetworkInfo
            if (networkInfo != null && networkInfo.isAvailable) {
                Toast.makeText(p0, "network is avaiable", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(p0, "network is unavaiable", Toast.LENGTH_SHORT).show()
            }
//            Toast.makeText(p0, "received local broadcast", Toast.LENGTH_SHORT).show()
        }
    }
}
