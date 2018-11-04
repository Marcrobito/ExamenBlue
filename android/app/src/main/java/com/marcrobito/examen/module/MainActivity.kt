package com.marcrobito.examen.module

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.marcrobito.examen.App

import com.marcrobito.examen.R
import com.marcrobito.examen.adapters.CustomAdapter
import com.marcrobito.examen.adapters.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import javax.inject.Inject
import android.content.Intent
import android.net.Uri


class MainActivity : AppCompatActivity(), MainContract.View, OnItemClickListener {
    override fun onItemClick(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this.application as App).getComponent().inject(this)
        setContentView(R.layout.activity_main)
    }

    override fun onResume() {
        super.onResume()
        presenter.setView(this)
    }

    override fun showData(data: List<Int>) {
        /*
        * val twitterAdapter = TwitterAdapter(twittersList, this)
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = twitterAdapter
        * */
        val queue = Volley.newRequestQueue(this)

        val request = object : JsonObjectRequest(Request.Method.GET,"https://hacker-news.firebaseio.com/v0/item/$data[0].json?print=pretty", null,
            Response.Listener<JSONObject> { response ->

                Log.d("Dial_M", response.toString())

            }, Response.ErrorListener { error ->
                Log.d("Dial_M", error.message.toString())
            }){

        }

        queue.add(request)

        val adapter = CustomAdapter(data, this)
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter



    }
}
