package com.marcrobito.examen.module

interface MainContract {
    interface View{
        fun showData(data:List<Int>)
    }

    interface Presenter{
        fun setView(v:View)
        fun dataFetched(data:List<Int>)
    }

    interface Model{
        fun setPresenter(p:Presenter)
    }
}