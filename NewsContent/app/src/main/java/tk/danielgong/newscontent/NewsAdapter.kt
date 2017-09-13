package tk.danielgong.newscontent

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.news_item.view.*

import kotlinx.android.synthetic.main.news_content.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by gongzhq on 2017/9/12.
 */
class NewsAdapter(private val frag: NewsTitleFragment, private val newsList: MutableList<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var newsTitleText = itemView.news_title
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent?.context).inflate(R.layout.news_item, parent, false)
            val holder = ViewHolder(view)
            view.onClick {
                val news = newsList.get(holder.adapterPosition)
                when(frag.isTwoPane) {
                    true -> {
                        (frag.news_content_fragment as NewsContentFragment).refresh(news.title, news.content)
                    }
                    false -> {
                        NewsContentActivity.actionStart(frag.context, news.title, news.content)
                    }
                }
            }
            return holder
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {

            val news = newsList.get(position)
            holder?.newsTitleText?.text = news.title
        }

        override fun getItemCount(): Int {
            return newsList.size
        }
}