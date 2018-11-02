package com.marcrobito.examen.module

import com.marcrobito.examen.network.hackernews.Api
import dagger.Module
import dagger.Provides
import javax.inject.Inject

@Module
class MainModule{

    @Provides
    fun providesMainPresenter(model: MainContract.Model):MainContract.Presenter = MainPresenter(model)

    @Provides
    @Inject
    fun providesMainModel(api:Api):MainContract.Model = MainModel(api)
}