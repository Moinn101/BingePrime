package com.moin.bingeprime.data.repository

import android.util.Log
import com.moin.bingeprime.data.model.MediaItem
import com.moin.bingeprime.data.network.WatchmodeApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.functions.BiFunction

class MediaRepository(private val api: WatchmodeApi) {
    private val apiKey = "DjYaWzito9rQh4wy5kextTUfZ9KXC7v4bn1HnZLy"
    private val TAG = "MediaRepository"

    fun getAllMedia(): Single<Pair<List<MediaItem>, List<MediaItem>>> {
        return Single.zip(
            api.searchSeries(apiKey),
            api.searchMovies(apiKey),
            BiFunction { seriesResponse, moviesResponse ->
                val tvSeries = seriesResponse.results.filter { it.type == "tv_series" }.map { media ->
                    MediaItem(
                        id = media.id,
                        name = media.name,
                        year = media.year,
                        image_url = media.image_url,
                        type = media.type
                    )
                }
                val movies = moviesResponse.results.filter { it.type == "movie" }.map { media ->
                    MediaItem(
                        id = media.id,
                        name = media.name,
                        year = media.year,
                        image_url = media.image_url,
                        type = media.type
                    )
                }
                Log.d(TAG, "TV Series Response: $tvSeries")
                Log.d(TAG, "Movies Response: $movies")
                Pair(tvSeries, movies)
            }
        )
    }
}