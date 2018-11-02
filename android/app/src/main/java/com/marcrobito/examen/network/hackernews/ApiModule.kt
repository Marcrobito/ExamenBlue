package com.marcrobito.examen.network.hackernews

import android.app.Application
import com.marcrobito.examen.network.NetworkManager
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File



@Module
class ApiModule(app: Application) {

    private val baseUrl = when(NetworkManager.NetworkManager().environment){
        NetworkManager.NetworkEnvironment.Production ->  "https://hacker-news.firebaseio.com/v0/"
        NetworkManager.NetworkEnvironment.QA ->  "https://hacker-news.firebaseio.com/v0/"
        NetworkManager.NetworkEnvironment.Staging ->  "https://hacker-news.firebaseio.com/v0/"
    }

    @Provides
    fun provideClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideRetrofit(baseUrl:String, client:OkHttpClient): Retrofit {


        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApiService(): Api = provideRetrofit(baseUrl, provideClient()).create(Api::class.java)


}