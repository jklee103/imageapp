package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager2.widget.ViewPager2
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.createContactsList
import com.example.jkapplication.presenter.MorePresenter
import com.example.jkapplication.view.BaseFragment
import com.example.jkapplication.view.CustomScroll
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.ScrollRecyclerAdapter
import com.example.jkapplication.view.decoration.ViewItemDecoration

private const val LOAD_TYPE = "glide"

class ScrollFragment : BaseFragment(), CustomScroll.onLoadMore, MainView {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    var adapter: ScrollRecyclerAdapter? = null
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var hashmap: HashMap<String, Int>
    lateinit var myscroll: CustomScroll
    lateinit var rootView: View
    lateinit var vp: ViewPager2

    var replace: Boolean = true
    var count = 1
    var mainHandler = Handler()
    override val presenter by lazy {
        MorePresenter(this)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_scroll, container, false)

        replace = true

        recyclerView = rootView.findViewById(R.id.f_scroll_rv_recyclerView)
        list = createContactsList(5)//demo list
        //recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        myscroll = CustomScroll(this) //????????? ??????
        myscroll.setLoaded()
        recyclerView.addOnScrollListener(myscroll)

        adapter = ScrollRecyclerAdapter(arrayListOf(), LOAD_TYPE) //????????? ??????
        recyclerView.adapter = adapter


        setHashmap() //????????? ?????????

        presenter.moreConnect(hashmap) //????????? ???????????? show

        swipeRefreshLayout =
            rootView.findViewById<SwipeRefreshLayout>(R.id.f_scroll_srl_refreshView)

        vp = this.activity!!.findViewById(R.id.view_pager)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.addItemDecoration(ViewItemDecoration())
        recyclerView.smoothScrollToPosition(0)

        recyclerView.addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
            override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
            }

            override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
                when (e.action) {
                    MotionEvent.ACTION_DOWN -> { // touch down
                        //vp.isUserInputEnabled = false
                    }
                    MotionEvent.ACTION_UP -> { //????????????
                        //vp.isUserInputEnabled = false
                    }
                }
                return false
            }

            override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
            }
        })

        swipeRefreshLayout.setOnRefreshListener { //onloadmore ????????? ??????????????? ????????????????????? ???????????????...
            count = 1
            setHashmap()
            replace = true
            presenter.setIsLast(false)
            presenter.moreConnect(hashmap)
            myscroll.setLoaded()
            Log.d("refresh6", "replaced")
            swipeRefreshLayout.isRefreshing = false //true??? ???????????? ??? ?????????

        }
    }

    override fun onLoadMore() {

        recyclerView.smoothScrollToPosition(recyclerView.layoutManager!!.itemCount)
        //Log.e("main", "load count is $count, ${checkLast()}")
        replace = false
        if (!checkLast())// ????????? ??????????????? ???
        {
            adapter?.addprogress()
            adapter?.notifyItemInserted(recyclerView.layoutManager!!.itemCount)
            mainHandler.postDelayed({
                adapter?.removeprogress()
                count++;
                hashmap = HashMap<String, Int>()
                hashmap.put("page", count)
                hashmap.put("perpage", 5)
                presenter.moreConnect(hashmap)
                myscroll.setLoaded()
            }, 2000)
        }


    }

    fun checkLast(): Boolean {
        return presenter.getIsLast()
    }

    fun setHashmap() {
        count = 1
        hashmap = HashMap<String, Int>()
        hashmap.put("page", 1)
        hashmap.put("perpage", 5)
    }

    companion object {
        var INSTANCE: ScrollFragment? = null

        fun getInstance(): ScrollFragment {
            if (INSTANCE == null)
                INSTANCE = ScrollFragment()
            return INSTANCE!!
        }
    }

    override fun show(items: ArrayList<Monster>) {
        if (replace || count == 1) {
            adapter!!.replaceAll(items)
            recyclerView.smoothScrollToPosition(list.size - 1)
            swipeRefreshLayout.isEnabled = false//????????? ????????? ?????? ????????? ?????????????????? ????????? ???????????? ???????????? ?????????
        } else {
            adapter?.addAll(items)
            swipeRefreshLayout.isEnabled = true
        }

    }

    override fun showError(error: Throwable) {
        TODO("Not yet implemented")
    }


}