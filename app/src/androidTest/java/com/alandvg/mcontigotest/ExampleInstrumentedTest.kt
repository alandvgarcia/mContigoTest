package com.alandvg.mcontigotest

import android.util.Log
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.alandvg.mcontigotest.api.ItunesApi
import com.alandvg.mcontigotest.entity.SearchResult

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        var recebeuDados = false

        assertEquals("com.alandvg.mcontigotest", appContext.packageName)

        ItunesApi.itunesService().searchMusicVideo("beyonce").enqueue(object : Callback<SearchResult>{
            override fun onFailure(call: Call<SearchResult>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<SearchResult>, response: Response<SearchResult>) {
                Log.d("Teste", response.body().toString())
            }

        })

    }
}
