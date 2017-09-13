package tk.danielgong.broadcastbestpractice

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.sdk25.coroutines.onClick

import org.jetbrains.anko.*
/**
 * Created by gongzhq on 2017/9/13.
 */
class LoginActivity: BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.onClick {
            val account = account.text.toString()
            val password = password.text.toString()
            if (account.equals("admin") && password.equals(("123456"))) {
                startActivity(intentFor<MainActivity>())
                finish()
            } else {
                toast("account or password is invalid")
            }
        }
    }
}