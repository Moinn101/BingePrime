package com.moin.bingeprime.data.network

import com.moin.bingeprime.data.model.MediaResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WatchmodeApi {
    @GET("autocomplete-search")
    fun searchSeries(
        @Query("apiKey") apiKey: String,
        @Query("search_value") searchValue: String = "series"
    ): Single<MediaResponse>

    @GET("autocomplete-search")
    fun searchMovies(
        @Query("apiKey") apiKey: String,
        @Query("search_value") searchValue: String = "movies"
    ): Single<MediaResponse>
}