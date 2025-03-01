package com.moin.bingeprime.data.model

data class MediaResponse(
    val results: List<MediaItem>
)

data class MediaItem(
    val id: Int,
    val name: String?,
    val year: Int?,
    val image_url: String?,
    val type: String?
)