package com.marcrobito.examen.module

import android.util.Log
import com.marcrobito.examen.network.hackernews.Api
import com.marcrobito.examen.pojos.HackersContent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class MainModel( val api: Api): MainContract.Model {

    private var presenter:MainContract.Presenter? = null

    override fun setPresenter(p: MainContract.Presenter) {
        presenter = p

        getAllNews()
    }

    private fun getAllNews() {

        val call: Call<List<Int>> = api.getContent()
        call.enqueue(object : Callback<List<Int>> {
            override fun onFailure(call: Call<List<Int>>?, t: Throwable?) {
            }

            override fun onResponse(call: Call<List<Int>>?, response: Response<List<Int>>?) {
                presenter!!.dataFetched(response?.body() as List<Int>)
            }
        })
    }

}