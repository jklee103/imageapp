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

private const val LOAD_TYPE = "glide"
class ButtonFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter: FrescoRecyclerAdapter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var ft: FragmentTransaction
    lateinit var presenter: GlidePresenter
    lateinit var button: Button
    lateinit var checkByString: String
    lateinit var hashmap:HashMap<String, Boolean>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_button, container, false)

        checkByString="true"

        recyclerView = rootView.findViewById(R.id.f_button_rv_recyclerView)
        list = createContactsList(5)//demo list
        recyclerView.setHasFixedSize(true)

        var context: Context = this!!.activity!!
        adapter = FrescoRecyclerAdapter(context, list,LOAD_TYPE) //여기 나중에 어댑터 손보면서 바꿔주기


        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = adapter


        presenter= GlidePresenter(adapter,list)//getlist

        hashmap = HashMap<String, Boolean>()
        hashmap.put("isMonster", true)

        presenter.postConnect(hashmap)

        swipeRefreshLayout = rootView.findViewById<SwipeRefreshLayout>(R.id.f_button_srl_refreshView)

        button = rootView.findViewById<Button>(R.id.f_button_btn_monster)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener{ //새로고침시 리스트 받아옴
            presenter.postConnect(hashmap)
            Log.d("refresh4","replaced")
            swipeRefreshLayout.isRefreshing = false //true로 해놓으면 안 없어짐
        }
        button.setOnClickListener {
            if(checkByString=="true"){
                checkByString="false"
                button.text = "OTHER"
                hashmap.clear()
                hashmap.put("isMonster", false)
                presenter.postConnect(hashmap)
                Log.d("btn click","to other")
            }else{
                checkByString="true"
                button.text="MONSTER"
                hashmap.clear()
                hashmap.put("isMonster", true)
                presenter.postConnect(hashmap)
                Log.d("btn click","to monster")
            }
        }


    }

    companion object {
        var INSTANCE: ButtonFragment? = null

        fun getInstance(): ButtonFragment {
            if (INSTANCE == null)
                INSTANCE = ButtonFragment()
            return INSTANCE!!
        }

        fun newInstance(): ButtonFragment {
            return ButtonFragment()
        }
    }
}