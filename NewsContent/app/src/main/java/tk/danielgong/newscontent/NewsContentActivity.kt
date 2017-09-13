package tk.danielgong.newscontent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.news_content.*

/**
 * Created by gongzhq on 2017/9/12.
 */
class NewsContentActivity: AppCompatActivity() {

    companion object {
        fun actionStart(context: Context, newsTitle: String, newsContent: String) {
            val intent = Intent(context, NewsContentActivity::class.java)
            intent.putExtra("news_title", newsTitle)
            intent.putExtra("news_content", newsContent)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_content)
        val newsTitle = intent.getStringExtra("news_title")
        val newsContent = intent.getStringExtra("news_content")
        (news_content_fragment as NewsContentFragment).refresh(newsTitle, newsContent)
    }
}