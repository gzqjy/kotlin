package tk.danielgong.recyclerview

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_recycler.*
import java.util.*

class RecyclerActivity : AppCompatActivity() {
    var fruitList: MutableList<Fruit> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        initFruits()
//        val layoutManager = LinearLayoutManager(this)
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
        recycler_view.layoutManager = layoutManager
//        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        val adapter = FruitAdapter(fruitList)
        recycler_view.adapter = adapter
//        recycler_view.
//        recycler_view.onItemClick { p0, p1, p2, p3 ->
//            val fruit = fruitList.get(p2)
//            toast(fruit.name)
//        }
    }

    fun initFruits() {
        for (i in 0..2) {
            fruitList.add(Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic))
            fruitList.add(Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic))
            fruitList.add(Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic))
            fruitList.add(Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic))
            fruitList.add(Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic))
            fruitList.add(Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic))
            fruitList.add(Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic))
            fruitList.add(Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic))
            fruitList.add(Fruit(getRandomLengthName("Chery"), R.drawable.cherry_pic))
            fruitList.add(Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic))
        }
    }

    fun getRandomLengthName(name: String): String {
        val random = Random()
        val length = random.nextInt(20) + 1
        val builder = StringBuilder()
        for (i in 0..length) {
            builder.append(name)
        }
        return builder.toString()
    }
}
