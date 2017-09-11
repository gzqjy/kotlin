package tk.danielgong.recyclerview

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fruit_item.view.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 * Created by gongzhq on 2017/9/11.
 */
class FruitAdapter(private val fruitList: MutableList<Fruit>): RecyclerView.Adapter<FruitAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        val holder = ViewHolder(view)
        holder.fruitView.onClick {
            view.context.toast(holder.fruitView.fruit_name.text)
        }
        return holder
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var fruitView: View = itemView
        fun bindFruit(fruit: Fruit) {
            with(fruit) {
                itemView.fruit_image.imageResource = fruit.imageId
                itemView.fruit_name.text = fruit.name
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.bindFruit(fruitList[position])
    }

    override fun getItemCount(): Int {
        val sizes = fruitList.size
        return fruitList.size
    }
}