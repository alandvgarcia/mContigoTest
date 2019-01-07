package com.alandvg.mcontigotest.entity

import com.google.gson.JsonArray

data class SearchResult(
    var resultCount: Long? = null,
    var results: JsonArray? = null
)