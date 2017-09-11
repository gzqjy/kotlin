package tk.danielgong.chat

/**
 * Created by gongzhq on 2017/9/11.
 */
class Msg (var content: String, var type: Int){
    companion object {
        val TYPE_RECV = 0
        val TYPE_SEND = 1
    }
}