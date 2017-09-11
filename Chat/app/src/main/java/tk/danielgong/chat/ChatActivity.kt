package tk.danielgong.chat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_chat.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class ChatActivity : AppCompatActivity() {
    var msgList: MutableList<Msg> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)
        initMsgs()
        recycler_view.layoutManager = LinearLayoutManager(this)
        val adapter = MsgAdapter(msgList)
        recycler_view.adapter = adapter
        send.onClick {
            val content = input_text.text
            if (content.length > 0) {
                val msg = Msg(content.toString(), Msg.TYPE_SEND)
                msgList.add(msg)
                adapter.notifyItemInserted(msgList.size - 1)
                recycler_view.scrollToPosition(msgList.size - 1)
                input_text.text.clear()
            }
        }
    }

    fun initMsgs(){
        val msg1 = Msg("Hello guy", Msg.TYPE_RECV)
        msgList.add(msg1)

        val msg2 = Msg("Hello, Who is that?", Msg.TYPE_SEND)
        msgList.add(msg2)

        val msg3 = Msg("This is Tom, Nice talking to you.", Msg.TYPE_RECV)
        msgList.add(msg3)
    }
}
