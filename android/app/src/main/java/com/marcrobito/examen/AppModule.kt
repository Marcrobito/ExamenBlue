package com.marcrobito.examen

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule( private var app: Application) {

    @Provides
    @Singleton
    fun providesApp(): Application {
        return app
    }
}