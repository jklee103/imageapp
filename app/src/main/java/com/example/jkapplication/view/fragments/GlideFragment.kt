package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.jkapplication.R
import com.example.jkapplication.data.ImagesResponse
import com.example.jkapplication.data.network.GlideRetrofit
import com.example.jkapplication.model.Monster
import com.example.jkapplication.model.createContactsList
import com.example.jkapplication.presenter.GlidePresenter
import com.example.jkapplication.view.MainView
import com.example.jkapplication.view.adapters.GlideRecyclerAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GlideFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter: GlideRecyclerAdapter
    lateinit var swipeRefreshLayout:SwipeRefreshLayout
    lateinit var ft:FragmentTransaction

    var retrofit = GlideRetrofit()
    var imglist = retrofit.response

    private fun connect() {

        imglist.enqueue(object : Callback<ImagesResponse?> { //enqueue랑 속도가 안맞아서 동기화 해야
            override fun onFailure(call: Call<ImagesResponse?>, t: Throwable) {
                Log.e("glide fragment", "fail")
            }

            override fun onResponse(
                call: Call<ImagesResponse?>,
                response: Response<ImagesResponse?>
            ) {
                val body = response.body()
                Log.d("glide fragment", "ok")
                list = body!!.images
                Log.d("item check", list[0].date)
                adapter.replaceAll(list)
            }

        })
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
        adapter = GlideRecyclerAdapter(context, list) //context호출 맞나....

        ft= fragmentManager?.beginTransaction()!!

        var presenter = GlidePresenter(this)
        //presenter.replaceAdapter(adapter) //데이터 갖고오는 함수
//        list = presenter.getNewData()
//        adapter.replaceAll(list)

        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerView.adapter = adapter

        connect() // list 받아오는 것인디..adapter연결때문에 여기 선언했는데 프레젠터로 옮길방법 찾기

        //swiperefresh
        swipeRefreshLayout = rootView.findViewById<SwipeRefreshLayout>(R.id.f_glide_srl_refreshView)

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        swipeRefreshLayout.setOnRefreshListener{
            //새로고침할때 코드
            adapter.replaceAll(list)
            Log.d("refresh","replaced")
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

        fun newInstance(): GlideFragment {
            return GlideFragment()
        }
    }

}