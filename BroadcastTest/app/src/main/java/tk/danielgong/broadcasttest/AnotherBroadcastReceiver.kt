package tk.danielgong.broadcasttest

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by gongzhq on 2017/9/13.
 */
class AnotherBroadcastReceiver: BroadcastReceiver() {
    override fun onReceive(p0: Context?, p1: Intent?) {
        Toast.makeText(p0, "received in AnotherBroadcastReceiver",
                Toast.LENGTH_SHORT).show();
        abortBroadcast()
    }
}