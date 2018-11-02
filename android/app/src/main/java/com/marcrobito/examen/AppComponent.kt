package com.marcrobito.examen

import com.marcrobito.examen.module.MainActivity
import com.marcrobito.examen.module.MainModule
import com.marcrobito.examen.network.hackernews.ApiModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, ApiModule::class, MainModule::class])

interface AppComponent {
    fun inject(mainActivity: MainActivity)
}