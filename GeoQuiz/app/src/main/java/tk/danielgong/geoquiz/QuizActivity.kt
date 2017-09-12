package tk.danielgong.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_quiz.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

import android.util.Log

class QuizActivity : AppCompatActivity() {
    val Questions = listOf(Question(R.string.question_1, true),
            Question(R.string.question_2, false),
            Question(R.string.question_3, false),
            Question(R.string.question_4, false),
            Question(R.string.question_5, true))
    var mCurrentIndex: Int = 0
    val CURRENT_INDEX = "current"
    val CHEAT_INDEX = "cheat"
    val TAG = "QuizActivity"
    val REQUEST_CODE_CHEAT = 0
    var mIsCheaters = mutableListOf<Boolean>(false, false, false, false, false)

    fun updateQuestion() {
        question_text_view.textResource = Questions.get(mCurrentIndex).mTextResId
    }

    fun checkAnswer(userPressedTrue: Boolean) {
        val answerIsTrue = Questions.get(mCurrentIndex).mAnswerTrue
        val isCheater = mIsCheaters.get(mCurrentIndex)
        var messageResId = 0
        when(isCheater) {
            true -> messageResId = R.string.judgment_toast
            false ->
                when (userPressedTrue) {
                    answerIsTrue -> messageResId = R.string.correct_toast
                    !answerIsTrue -> messageResId = R.string.incorrect_toast
                }
        }
        toast(messageResId)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        Log.i(TAG, "onSaveInstanceState")
        outState?.putInt(CURRENT_INDEX, mCurrentIndex)
        val isCheater = mIsCheaters.get(mCurrentIndex)
        outState?.putBoolean(CHEAT_INDEX, isCheater)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK)
            return
        if (requestCode == REQUEST_CODE_CHEAT) {
            if (data != null) {
                val isCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false)
                mIsCheaters.set(mCurrentIndex, isCheater)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(savedInstanceState: Bundle?) called")
        setContentView(R.layout.activity_quiz)

        false_button.onClick { checkAnswer(false) }
        true_button.onClick { checkAnswer(true) }
        next_button.onClick {
            mCurrentIndex = (mCurrentIndex + 1) % Questions.size
            updateQuestion()
        }

        question_text_view.onClick {
            mCurrentIndex = (mCurrentIndex + 1) % Questions.size
            updateQuestion()
        }
        prev_button.onClick {
            mCurrentIndex = (mCurrentIndex - 1 + Questions.size) % Questions.size
            updateQuestion()
        }
        cheat_button.onClick {
            val answerIsTrue = Questions.get(mCurrentIndex).mAnswerTrue
            startActivityForResult(intentFor<CheatActivity>(CheatActivity.EXTRA_ANSWER_IS_TRUE to answerIsTrue), REQUEST_CODE_CHEAT)
            toast("show cheat $answerIsTrue")
        }
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(CURRENT_INDEX, 0)
            val isCheater = savedInstanceState.getBoolean(CHEAT_INDEX, false)
            mIsCheaters.set(mCurrentIndex, isCheater)
        }
        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}
