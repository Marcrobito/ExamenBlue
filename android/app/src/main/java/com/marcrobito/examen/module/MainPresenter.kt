package com.marcrobito.examen.module

import android.util.Log

class MainPresenter(val model: MainContract.Model): MainContract.Presenter {


    private var view:MainContract.View? = null

    override fun setView(v: MainContract.View) {
        view = v


        model.setPresenter(this)
    }

    override fun dataFetched(data: List<Int>) {
        view!!.showData(data)
    }

}