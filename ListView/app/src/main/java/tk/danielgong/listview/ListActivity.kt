package tk.danielgong.listview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import kotlinx.android.synthetic.main.activity_list.*

import org.jetbrains.anko.*
import org.jetbrains.anko.sdk25.coroutines.onItemClick

class ListActivity : AppCompatActivity() {
    var fruitList: MutableList<Fruit> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        initFruits()
        val adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        list_view.adapter = adapter
        list_view.onItemClick { p0, p1, p2, p3 ->
                val fruit = fruitList.get(p2)
                toast(fruit.name)
        }
    }

    fun initFruits() {
        for (i in 0..2) {
            fruitList.add(Fruit("Apple", R.drawable.apple_pic))
            fruitList.add(Fruit("Banana", R.drawable.banana_pic))
            fruitList.add(Fruit("Orange", R.drawable.orange_pic))
            fruitList.add(Fruit("Watermelon", R.drawable.watermelon_pic))
            fruitList.add(Fruit("Pear", R.drawable.pear_pic))
            fruitList.add(Fruit("Grape", R.drawable.grape_pic))
            fruitList.add(Fruit("Pineapple", R.drawable.pineapple_pic))
            fruitList.add(Fruit("Strawberry", R.drawable.strawberry_pic))
            fruitList.add(Fruit("Chery", R.drawable.cherry_pic))
            fruitList.add(Fruit("Mango", R.drawable.mango_pic))
        }
    }
}
