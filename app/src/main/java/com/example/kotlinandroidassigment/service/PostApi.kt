package com.example.kotlinandroidassigment.service

import com.example.kotlinandroidassigment.model.Post
import retrofit2.Call
import retrofit2.http.GET

interface PostApi {
    @GET("/todos")
    fun getPosts() : Call<List<Post>>
}