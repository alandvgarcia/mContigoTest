package com.alandvg.mcontigotest

import android.util.Log
import androidx.test.runner.AndroidJUnit4
import com.alandvg.mcontigotest.api.ItunesApi
import com.alandvg.mcontigotest.entity.SearchResult
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


@RunWith(AndroidJUnit4::class)
class ItunesApiTest{

    @Test
    fun requestSearch(){
        ItunesApi.itunesService().searchMusicVideo("beyonce").enqueue(object : Callback<SearchResult> {
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {

                Log.d("Teste", response.body().toString())
            }

        })
    }


}

