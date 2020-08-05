package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.createContactsList
import com.example.jkapplication.presenter.MorePresenter
import com.example.jkapplication.view.BaseFragment
import com.example.jkapplication.view.CustomScroll
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.ScrollRecyclerAdapter

private const val LOAD_TYPE = "glide"

class ScrollFragment : BaseFragment(), CustomScroll.onLoadMore,MainView {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter: ScrollRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var hashmap: HashMap<String, Int>
    lateinit var myscroll: CustomScroll
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
        var rootView = inflater.inflate(R.layout.fragment_scroll, container, false)

        recyclerView = rootView.findViewById(R.id.f_scroll_rv_recyclerView)
        list = createContactsList(5)//demo list
        recyclerView.setHasFixedSize(true)

        var context: Context = this!!.activity!!

        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        myscroll = CustomScroll(this)
        myscroll.setLoaded()
        recyclerView.addOnScrollListener(myscroll)

        setHashmap()

        presenter.moreConnect(hashmap, true)

        swipeRefreshLayout =
            rootView.findViewById<SwipeRefreshLayout>(R.id.f_scroll_srl_refreshView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener { //onloadmore 끝까지 호출후에는 새로고침을해도 호출이안됨...
            count = 1
            setHashmap()
            presenter.setIsLast(false)
            presenter.moreConnect(hashmap, true)
            Log.d("refresh6", "replaced")
            myscroll.setLoaded()
            swipeRefreshLayout.isRefreshing = false //true로 해놓으면 안 없어짐
        }
    }

    override fun onLoadMore() {
        recyclerView.smoothScrollToPosition(recyclerView.layoutManager!!.itemCount)
        Log.e("main", "load count is $count, ${checkLast()}")

        if (!checkLast())// 여기에 프로그레스 추
        {
            adapter.addprogress()
            adapter.notifyItemInserted(recyclerView.layoutManager!!.itemCount)
            mainHandler.postDelayed({
                adapter.removeprogress()
                count++;
                hashmap = HashMap<String, Int>()
                hashmap.put("page", count)
                hashmap.put("perpage", 5)
                presenter.moreConnect(hashmap, false)
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
        adapter = ScrollRecyclerAdapter(items, LOAD_TYPE)
        recyclerView.adapter=adapter
    }

    override fun showError(error: Throwable) {
        TODO("Not yet implemented")
    }

}