package com.alandvg.mcontigotest.api

import com.alandvg.mcontigotest.entity.SearchResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItunesService {

    @GET("search")
    fun searchMusicVideo(
        @Query("term") searchString: String,
        @Query("entity") entity: String = "musicVideo"
    ): Call<SearchResult>

}