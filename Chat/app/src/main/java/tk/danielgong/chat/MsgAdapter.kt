package tk.danielgong.chat

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.msg_item.view.*

/**
 * Created by gongzhq on 2017/9/11.
 */
class MsgAdapter(private val msgList: MutableList<Msg>): RecyclerView.Adapter<MsgAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.msg_item, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val leftLayout = itemView.left_layout
        val rightLayout = itemView.right_layout
        val leftMsg = itemView.left_msg
        val rightMsg = itemView.right_msg
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val msg = msgList.get(position)
        when(msg.type) {
            Msg.TYPE_RECV -> {
                holder.leftLayout.visibility = View.VISIBLE
                holder.rightLayout.visibility = View.GONE
                holder.leftMsg.text = msg.content
            }
            Msg.TYPE_SEND -> {
                holder.rightLayout.visibility = View.VISIBLE
                holder.leftLayout.visibility = View.GONE
                holder.rightMsg.text = msg.content
            }
        }
    }

    override fun getItemCount(): Int {
        return msgList.size
    }
}