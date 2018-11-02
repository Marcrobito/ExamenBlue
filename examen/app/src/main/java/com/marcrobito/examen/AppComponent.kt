package com.marcrobito.examen

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])

interface AppComponent {
    fun inject(app:App)
}