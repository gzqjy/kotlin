package tk.danielgong.newscontent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_content_frag.*

/**
 * Created by gongzhq on 2017/9/12.
 */
class NewsContentFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.news_content_frag, container, false)
        return view
    }

    fun refresh(newsTitle: String, newsContent: String) {
        visibility_layout.visibility = View.VISIBLE
        news_title.text = newsTitle
        news_content.text = newsContent
    }
}