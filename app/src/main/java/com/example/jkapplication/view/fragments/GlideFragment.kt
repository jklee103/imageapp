package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.createContactsList
import com.example.jkapplication.presenter.GlidePresenter
import com.example.jkapplication.view.BaseFragment
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.GlideRecyclerAdapter

private const val LOAD_TYPE = "glide"

class GlideFragment : BaseFragment(), MainView {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter: GlideRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override val presenter by lazy {
        GlidePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_glide, container, false)
        recyclerView = rootView.findViewById(R.id.f_glide_rv_recyclerView)
        list = createContactsList(5)//demo list
        recyclerView.setHasFixedSize(true)

        var context: Context = this!!.activity!!


        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        presenter.connect()

        //swiperefresh
        swipeRefreshLayout = rootView.findViewById<SwipeRefreshLayout>(R.id.f_glide_srl_refreshView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            //새로고침할때 코드
            presenter.connect()
            Log.d("refresh", "replaced")
            swipeRefreshLayout.isRefreshing = false
        }

    }

    companion object {
        var INSTANCE: GlideFragment? = null

        fun getInstance(): GlideFragment {
            if (INSTANCE == null)
                INSTANCE = GlideFragment()
            return INSTANCE!!
        }
    }

    override fun show(items: ArrayList<Monster>) { //어댑터 생성, 리사이클러뷰에 어댑터 붙이
        val adapter = GlideRecyclerAdapter(items, LOAD_TYPE)
        recyclerView.adapter = adapter
    }

    override fun showError(error: Throwable) {
        Log.e("tab1", "Error: ${error.message}")
        error.printStackTrace()
    }

}