package tk.danielgong.criminalintent

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime_list.*
import kotlinx.android.synthetic.main.list_item_crime.view.*
import kotlinx.coroutines.experimental.channels.actor
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.*
/**
 * Created by gongzhq on 2017/9/14.
 */

class CrimeListFragment: Fragment() {
    companion object {
        val REQUEST_CRIME = 1
    }

    private var mAdapter: CrimeAdapter ?= null
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        crime_recycler_view.layoutManager = LinearLayoutManager(activity)
        updateUi()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_crime_list, container, false);
        return view
    }

    override fun onResume() {
        super.onResume()
        updateUi()
    }

    fun updateUi() {
        val crimeLab = CrimeLab
        val crimes = crimeLab.mCrimes
        if (mAdapter == null) {
            mAdapter = CrimeAdapter(this, crimes)
            crime_recycler_view.adapter = mAdapter
        } else {
            mAdapter!!.notifyDataSetChanged()
        }
    }

    class CrimeAdapter(private val frag: Fragment, private val crimeList: MutableList<Crime>): RecyclerView.Adapter<CrimeAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_crime, parent, false)
            val holder = ViewHolder(view)
            view.onClick {
//                frag.startActivity<CrimeActivity>(CrimeActivity.EXTRA_CRIME_ID to holder.mCrime!!.mId)
//                val intent = CrimeActivity.newIntent(frag.activity, holder.mCrime!!.mId)
                val intent = CrimePagerActivity.newIntent(frag.activity, holder.mCrime!!.mId)
                frag.startActivity(intent)
//                val intent = Intent(frag.activity, CrimeActivity::class.java)
//                frag.startActivity(intent)
//                view.context.toast("${holder.mCrime?.mTitle} clicked!")
            }
            return holder
        }

        class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
            var mCrime:Crime ?= null
            var mTitleTextView = itemView.list_item_crime_title_text_view
            var mDateTextView = itemView.list_item_crime_date_text_view
            var mSolvedCheckBox = itemView.list_item_crime_solved_check_box
            fun bindCrime(crime: Crime) {
                mCrime = crime
                mTitleTextView.setText(mCrime!!.mTitle)
                mDateTextView.setText(mCrime!!.mDate.toString())
                mSolvedCheckBox.isChecked = mCrime!!.mSolved
            }
        }

        override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
            val crime = crimeList.get(position)
            holder?.bindCrime(crime)
        }

        override fun getItemCount(): Int {
            return crimeList.size
        }
    }
}

