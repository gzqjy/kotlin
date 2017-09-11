package tk.danielgong.listview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.fruit_item.view.*

/**
 * Created by gongzhq on 2017/9/11.
 */
class FruitAdapter(context: Context, resource: Int, list: MutableList<Fruit>) :
        ArrayAdapter<Fruit>(context, resource, list) {
    val resource = resource

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
//        return super.getView(position, convertView, parent)
        val fruit = getItem(position)
        var view: View ?= null
        var viewHolder: ViewHolder = ViewHolder()
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(resource, parent, false)
            viewHolder.fruitImage = view.fruit_image
            viewHolder.fruitName = view.fruit_name
            view.tag = viewHolder
        }
        else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

//        val view = LayoutInflater.from(context).inflate(resource, parent, false)
//        view!!.fruit_image.setImageResource(fruit.imageId)
//        view!!.fruit_name.setText(fruit.name)
        viewHolder.fruitImage?.setImageResource(fruit.imageId)
        viewHolder.fruitName?.setText(fruit.name)
        return view!!

    }

    class ViewHolder {
        var fruitImage: ImageView ?= null
        var fruitName: TextView ?= null
    }
}