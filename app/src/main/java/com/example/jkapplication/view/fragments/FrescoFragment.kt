package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.createContactsList
import com.example.jkapplication.presenter.GlidePresenter
import com.example.jkapplication.view.BaseFragment
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.FrescoRecyclerAdapter
import com.example.jkapplication.view.decoration.ViewItemDecoration

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val LOAD_TYPE = "fresco"

class FrescoFragment : BaseFragment(), MainView {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter: FrescoRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    override val presenter by lazy {
        GlidePresenter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_fresco, container, false)

        recyclerView = rootView.findViewById(R.id.f_fresco_rv_recyclerView)
        list = createContactsList(5)//demo list
        recyclerView.setHasFixedSize(true)

        var context: Context = this!!.activity!!

        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        presenter.connect()

        swipeRefreshLayout =
            rootView.findViewById<SwipeRefreshLayout>(R.id.f_fresco_srl_refreshView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener {
            presenter.connect()
            Log.d("refresh3", "replaced")
            swipeRefreshLayout.isRefreshing = false
        }
        recyclerView.addItemDecoration(ViewItemDecoration())

    }

    companion object {
        var INSTANCE: FrescoFragment? = null

        fun getInstance(): FrescoFragment {
            if (INSTANCE == null)
                INSTANCE = FrescoFragment()
            return INSTANCE!!
        }

        fun newInstance(): FrescoFragment {
            return FrescoFragment()
        }
    }

    override fun show(items: ArrayList<Monster>) {
        val adapter = FrescoRecyclerAdapter(items, LOAD_TYPE)
        recyclerView.adapter = adapter
    }

    override fun showError(error: Throwable) {
        TODO("Not yet implemented")
    }
}