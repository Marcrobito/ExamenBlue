package com.marcrobito.examen

import android.app.Application
import com.marcrobito.examen.network.hackernews.ApiModule

class App:Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .apiModule(ApiModule(this))
            .build()

    }

    fun getComponent() = component
}