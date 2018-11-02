package com.marcrobito.examen

import android.app.Application

class App:Application() {
    private lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
            .apiModule(AppModule(this))
            .build()

    }

    fun getComponent() = component
}