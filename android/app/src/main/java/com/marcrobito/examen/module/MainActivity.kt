package com.marcrobito.examen.module

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.marcrobito.examen.App

import com.marcrobito.examen.R
import com.marcrobito.examen.adapters.CustomAdapter
import com.marcrobito.examen.adapters.OnItemClickListener
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainContract.View {

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
        val adapter = CustomAdapter(data, this)
        recycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = adapter



    }
}
