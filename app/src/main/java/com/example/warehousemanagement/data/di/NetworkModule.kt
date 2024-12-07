package com.example.warehousemanagement.data.di

import com.example.warehousemanagement.data.repository.ApiWarehouse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesAppRetrofit(): ApiWarehouse = Retrofit.Builder()
        .baseUrl(RetrofitHandle.BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiWarehouse::class.java)
}

val loggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client = OkHttpClient.Builder()
    .addInterceptor(loggingInterceptor)
    .build()


object RetrofitHandle {
    const val API_KEY: String =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1MTQ5ZjdiODkyYzU1YzdjMTQwYTYwZTU1YTkyNWI0NiIsIm5iZiI6MTcyNDM4MDc5OC4zOTY2NDYsInN1YiI6IjY2YzJiOTQwYjE1ZTA5OWJhZjVkODY5YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.Tb5FqU0m3HXATFo_n4Z31R_BVEy5g5v0OPvjszt20to"

    // const val BASE_URL: String = "http://192.168.59.1:8081/"
    const val BASE_URL: String = "http://192.168.59.1:8081/"
}