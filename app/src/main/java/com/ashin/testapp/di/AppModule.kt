package com.ashin.testapp.di

import com.ashin.testapp.Repository.MainRepository
import com.ashin.testapp.network.ApiInterface
import com.ashin.testapp.network.ApiServiceImplementation
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.Builder

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun providesUrl()="https://jsonplaceholder.typicode.com/"

    @Provides
    @Singleton
    fun providesApiService(url:String):ApiInterface{
        val logging =  HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val client: OkHttpClient = Builder()
            .addInterceptor(logging)
            .build()
       return Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(ApiInterface::class.java)
    }



    @Provides
    @Singleton
    fun providesApiServiceImplent()=ApiServiceImplementation(providesApiService(providesUrl()))

    @Provides
    @Singleton
    fun providesMainRepository()=MainRepository(providesApiServiceImplent())

}