package com.moin.bingeprime.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.moin.bingeprime.data.model.MediaItem
import com.moin.bingeprime.data.repository.MediaRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers

class MediaViewModel(private val repository: MediaRepository) : ViewModel() {
    private val _movies = MutableLiveData<List<MediaItem>>()
    val movies: LiveData<List<MediaItem>> = _movies

    private val _tvShows = MutableLiveData<List<MediaItem>>()
    val tvShows: LiveData<List<MediaItem>> = _tvShows

    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val disposables = CompositeDisposable()
    private val TAG = "MediaViewModel"

    init {
        fetchMedia()
    }

    private fun fetchMedia() {
        _isLoading.value = true
        disposables.add(
            repository.getAllMedia()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { (tvShowsList, moviesList) ->
                        Log.d(TAG, "TV Shows List: $tvShowsList")
                        Log.d(TAG, "Movies List: $moviesList")
                        _tvShows.value = tvShowsList
                        _movies.value = moviesList
                        _isLoading.value = false
                    },
                    { error ->
                        Log.e(TAG, "Error fetching media: ${error.message}", error)
                        _error.value = error.message
                        _isLoading.value = false
                    }
                )
        )
    }

    fun clearError() {
        _error.value = null
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}