package com.example.jkapplication.view.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

class GlideFragment : Fragment(){
    lateinit var recyclerView: RecyclerView
    lateinit var list: ArrayList<Monster>
    lateinit var adapter:GlideRecyclerAdapter

    var retrofit = GlideRetrofit()
    var imglist = retrofit.response

    private fun connect() {

        imglist.enqueue(object : Callback<ImagesResponse?> { //enqueue랑 속도가 안맞아서 동기화 해야
            override fun onFailure(call: Call<ImagesResponse?>, t: Throwable) {
                Log.e("glide presenter", "fail")
            }

            override fun onResponse(
                call: Call<ImagesResponse?>,
                response: Response<ImagesResponse?>
            ) {
                val body = response.body()
                Log.e("glide presenter", "ok")
                list = body!!.images
                Log.d("item check",list[0].date)
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

        var context:Context = this!!.activity!!
        adapter = GlideRecyclerAdapter(context,list) //context호출 맞나....

        var presenter = GlidePresenter(this)
        //presenter.replaceAdapter(adapter) //데이터 갖고오는 함수
//        list = presenter.getNewData()
//        adapter.replaceAll(list)

        recyclerView.layoutManager= LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        recyclerView.adapter = adapter

        connect()


        return rootView
    }


    companion object {
        var INSTANCE: GlideFragment? = null

        fun getInstance(): GlideFragment {
            if (INSTANCE == null)
                INSTANCE = GlideFragment()
            return INSTANCE!!
        }
        
        fun newInstance():GlideFragment {
            return GlideFragment()
        }
    }
}