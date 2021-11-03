package com.example.kotlinandroidassigment.fragment

import android.app.Service
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinandroidassigment.R
import com.example.kotlinandroidassigment.adapter.ToDoFragmentAdapter
import com.example.kotlinandroidassigment.model.Post
import com.example.kotlinandroidassigment.service.Client
import com.example.kotlinandroidassigment.service.Utils
import okhttp3.OkHttpClient
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class ToDoFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.listView)
        recyclerView.layoutManager= LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        getPosts()
    }
    private fun getPosts(){
        Client.getApiService().getPosts().enqueue(object : Callback<List<Post>>{

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                println(t.localizedMessage)
            }
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                val posts : List<Post?>? = response.body()
                recyclerView.adapter = posts?.let {
                    ToDoFragmentAdapter(it,context!!)
                }
            }
        })
    }

}