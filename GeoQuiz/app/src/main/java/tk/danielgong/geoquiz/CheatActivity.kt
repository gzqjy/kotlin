package tk.danielgong.geoquiz

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.app.Activity
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import kotlinx.android.synthetic.main.activity_cheat.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.textResource

class CheatActivity : AppCompatActivity() {
    companion object {
        val EXTRA_ANSWER_IS_TRUE = "tk.danielgong.geoquiz.answer_is_true"
        val EXTRA_ANSWER_SHOWN = "tk.danielgong.geoquiz.answer_shown"
    }
    val TAG = "CheatActivity"
    var mAnswerIsTrue = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)
        Log.d(TAG, "onCreate")
        mAnswerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        if (savedInstanceState != null) {
            mAnswerIsTrue = savedInstanceState.getBoolean(EXTRA_ANSWER_IS_TRUE, false)
            when (mAnswerIsTrue) {
                true -> answer_text_view.textResource = R.string.true_button
                false -> answer_text_view.textResource = R.string.false_button
            }
            setAnswerShownResult(true)
        }
        val apiLevel = Build.VERSION.SDK_INT
        api_version.text = "api level $apiLevel"
        show_anser_button.onClick {
            when(mAnswerIsTrue) {
                true -> answer_text_view.textResource = R.string.true_button
                false -> answer_text_view.textResource = R.string.false_button
            }
            setAnswerShownResult(true)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                val cx = show_anser_button.width / 2
                val cy = show_anser_button.height / 2
                val radius = show_anser_button.width.toFloat()
                val anim = ViewAnimationUtils.createCircularReveal(show_anser_button, cx, cy, radius, 0F)
                anim.addListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(p0: Animator?) {
                        super.onAnimationEnd(p0)
                        show_anser_button.visibility = View.INVISIBLE
//                        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                    }
                })
            } else {
                show_anser_button.visibility = View.INVISIBLE
            }
        }
    }

    private fun setAnswerShownResult(isAnswerShown: Boolean){
        intent.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown)
        setResult(Activity.RESULT_OK, intent)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState?.putBoolean(EXTRA_ANSWER_IS_TRUE, mAnswerIsTrue)
    }
}
