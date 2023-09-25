package com.haryop.artgalleryviewerapp.data.repo

import com.haryop.artgalleryviewerapp.data.api.ApiService
import javax.inject.Inject

class RemoteArtworksRepo
@Inject
constructor(private val galleryApiService: ApiService) {
    suspend fun getArtworks(page:String) = galleryApiService.getArtworks(page)
}