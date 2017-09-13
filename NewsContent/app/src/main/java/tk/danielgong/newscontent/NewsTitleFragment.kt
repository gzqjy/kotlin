package tk.danielgong.newscontent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.news_title_frag.*
import java.util.*


/**
 * Created by gongzhq on 2017/9/12.
 */
class NewsTitleFragment: Fragment() {

    var isTwoPane = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        news_title_recycler_view?.layoutManager = LinearLayoutManager(activity)
        val adapter = NewsAdapter(this, getNews())
        news_title_recycler_view.adapter = adapter
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.news_title_frag, container, false)
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        when(activity.news_content_layout) {
            null -> isTwoPane = false
            else -> isTwoPane = true
        }
    }

    fun getNews(): MutableList<News> {
        val newsList = mutableListOf<News>()
        for(i in 0..50){
            val news = News("This is news title $i", getRandomLengthContent("This is news content $i"))
            newsList.add(news)
        }
        return newsList
    }

    fun getRandomLengthContent(content: String): String {
        val random = Random()
        val length = random.nextInt(20) + 1
        val builder = StringBuilder()
        for (i in 0..length) {
            builder.append(content)
        }
        return builder.toString()
    }
}