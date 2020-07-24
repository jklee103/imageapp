package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.createContactsList
import com.example.jkapplication.presenter.GlidePresenter
import com.example.jkapplication.view.adapters.GlideRecyclerAdapter

class PicassoFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter: GlideRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var ft: FragmentTransaction
    lateinit var presenter:GlidePresenter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_picasso, container, false)
        recyclerView = rootView.findViewById(R.id.f_picasso_rv_recyclerView)
        list = createContactsList(5)//demo list
        recyclerView.setHasFixedSize(true)

        var context: Context = this!!.activity!!
        adapter = GlideRecyclerAdapter(context, list) //여기 나중에 어댑터 손보면서 바꿔주기


        recyclerView.layoutManager = GridLayoutManager(context,2)//recyclerview adapter
        recyclerView.adapter = adapter


        presenter= GlidePresenter(adapter,list)//getlist
        presenter.connect()

        swipeRefreshLayout = rootView.findViewById<SwipeRefreshLayout>(R.id.f_picasso_srl_refreshView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener{
            presenter.connect()
            Log.d("refresh2","replaced")
            swipeRefreshLayout.isRefreshing = false
        }

    }

    companion object {
        var INSTANCE: PicassoFragment? = null

        fun getInstance(): PicassoFragment {
            if (INSTANCE == null)
                INSTANCE = PicassoFragment()
            return INSTANCE!!
        }

        fun newInstance(): PicassoFragment {
            return PicassoFragment()
        }
    }
}