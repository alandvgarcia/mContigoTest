package com.alandvg.mcontigotest.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alandvg.mcontigotest.R
import com.alandvg.mcontigotest.api.ItunesApi
import com.alandvg.mcontigotest.entity.SearchResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


//        ItunesApi.itunesService().searchMusicVideo("beyonce").enqueue(object : Callback<SearchResult> {
//            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
//                Log.d("Teste", "erro")
//            }
//
//            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
//                Log.d("Teste", response.body().toString())
//
//
//
//
//            }
//
//
//
//        })



    }
}
