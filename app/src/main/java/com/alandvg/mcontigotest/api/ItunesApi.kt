package com.alandvg.mcontigotest.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItunesApi {

    companion object {

        val BASE_URL = "https://itunes.apple.com/"

        fun provideRetrofit(baseUrl: String): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun itunesService() = provideRetrofit(BASE_URL).create(ItunesService::class.java)

    }
}