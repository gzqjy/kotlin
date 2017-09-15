package tk.danielgong.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import kotlinx.android.synthetic.main.fragment_crime_list.*
import kotlinx.android.synthetic.main.list_item_crime.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import android.support.v7.app.AppCompatActivity

/*
 * Created by gongzhq on 2017/9/14.
 */

class CrimeListFragment: Fragment() {
//    companion object {
//        val REQUEST_CRIME = 1
//    }

    private var mAdapter: CrimeAdapter ?= null
    private var mSubtitleVisible = false
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crime_recycler_view.layoutManager = LinearLayoutManager(activity)
        updateUi()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater?.inflate(R.layout.fragment_crime_list, container, false)
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_crime_list, menu)
        val subtitleItem = menu?.findItem(R.id.menu_item_show_subtitle)
        if (mSubtitleVisible) {
            subtitleItem?.title = getString(R.string.hide_subtitle)
        } else {
            subtitleItem?.title = getString(R.string.show_subtitle)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu_item_new_crime -> {
                val crime = Crime()
                CrimeLab.addCrime(crime)
                val intent = CrimePagerActivity.newIntent(activity, crime.mId)
                startActivity(intent)
                return true
            }
            R.id.menu_item_show_subtitle -> {
                mSubtitleVisible = !mSubtitleVisible
                activity.invalidateOptionsMenu()
                updateSubtitle()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun updateSubtitle() {
        var subtitle = resources.getQuantityString(R.plurals.subtitle_plural, CrimeLab.mCrimes!!.size, CrimeLab.mCrimes!!.size)
        if(!mSubtitleVisible) {
            subtitle = null
        }

        val activity = activity as AppCompatActivity
        activity.supportActionBar!!.subtitle = subtitle
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    private fun updateUi() {
        val crimeLab = CrimeLab
        val crimes = crimeLab.mCrimes
        when(crimes?.size) {
            null -> return
            0 -> {
                crime_set_empty_text_view.visibility = View.VISIBLE
                crime_recycler_view.visibility = View.GONE
            }
            else -> {
                crime_set_empty_text_view.visibility = View.GONE
                crime_recycler_view.visibility = View.VISIBLE
                if (mAdapter == null) {
                    mAdapter = CrimeAdapter(this, crimes)
                    crime_recycler_view.adapter = mAdapter
                } else {
                    mAdapter!!.notifyDataSetChanged()
                }
            }
        }
        updateSubtitle()
    }

    class CrimeAdapter(private val frag: Fragment, private val crimeList: MutableList<Crime>): RecyclerView.Adapter<CrimeAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime, parent, false)
            val holder = ViewHolder(view)
            view.onClick {
                val intent = CrimePagerActivity.newIntent(frag.activity, holder.mCrime!!.mId)
                frag.startActivity(intent)
            }
            return holder
        }

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var mCrime:Crime ?= null
            private var mTitleTextView = itemView.list_item_crime_title_text_view
            private var mDateTextView = itemView.list_item_crime_date_text_view
            private var mSolvedCheckBox = itemView.list_item_crime_solved_check_box
            fun bindCrime(crime: Crime) {
                mCrime = crime
                mTitleTextView.text = mCrime!!.mTitle
                mDateTextView.text = mCrime!!.mDate.toString()
                mSolvedCheckBox.isChecked = mCrime!!.mSolved
            }
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val crime = crimeList[position]
            holder?.bindCrime(crime)
        }

        override fun getItemCount(): Int {
            return crimeList.size
        }
    }
}

