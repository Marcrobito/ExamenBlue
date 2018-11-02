package com.marcrobito.examen.network.hackernews

import com.marcrobito.examen.pojos.HackersContent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("topstories.json?print=pretty")
    fun getContent(): Call<List<Int>>

    @GET("item/?")
    fun getNew(@Query("id") id:String): Call<HackersContent>
}