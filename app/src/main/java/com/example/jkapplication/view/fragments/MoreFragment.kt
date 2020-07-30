package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jkapplication.R
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.createContactsList
import com.example.jkapplication.presenter.GlidePresenter
import com.example.jkapplication.view.adapters.FrescoRecyclerAdapter
import com.example.jkapplication.view.adapters.MoreRecyclerAdapter
import kotlin.properties.Delegates

private const val LOAD_TYPE = "glide"

class MoreFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter: MoreRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var presenter: GlidePresenter
    lateinit var button: Button
    lateinit var hashmap: HashMap<String, Int>

    var count=1//페이지

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_more, container, false)

        recyclerView = rootView.findViewById(R.id.f_more_rv_recyclerView)
        list = createContactsList(5)//demo list
        recyclerView.setHasFixedSize(true)

        var context: Context = this!!.activity!!
        adapter = MoreRecyclerAdapter(context, list, LOAD_TYPE) //여기 나중에 어댑터 손보면서 바꿔주기


        recyclerView.layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        recyclerView.adapter = adapter


        presenter = GlidePresenter(adapter, list)//getlist

        setHashmap()

        presenter.moreConnect(hashmap, true)

        swipeRefreshLayout =
            rootView.findViewById<SwipeRefreshLayout>(R.id.f_more_srl_refreshView)


        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener { //새로고침시 리스트 받아옴
            count=1
            setHashmap()
            presenter.setIsLast(false)
            presenter.moreConnect(hashmap, true)
            Log.d("refresh5", "replaced")
            swipeRefreshLayout.isRefreshing = false //true로 해놓으면 안 없어짐
        }
    }

    fun setHashmap(){
        count=1
        hashmap = HashMap<String, Int>()
        hashmap.put("page", 1)
        hashmap.put("perpage", 5)
    }

    fun onLoadMore(){

        count++;
        hashmap = HashMap<String, Int>()
        hashmap.put("page", count)
        hashmap.put("perpage", 5)
        presenter.moreConnect(hashmap, false)

    }
    fun checkLast():Boolean{
        return presenter.getIsLast()
    }


    companion object {
        var INSTANCE: MoreFragment? = null

        fun getInstance(): MoreFragment {
            if (INSTANCE == null)
                INSTANCE = MoreFragment()
            return INSTANCE!!
        }

        fun newInstance(): MoreFragment {
            return MoreFragment()
        }
    }
}