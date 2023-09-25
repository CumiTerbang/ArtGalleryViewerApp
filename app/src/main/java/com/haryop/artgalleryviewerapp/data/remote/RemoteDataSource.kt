package com.haryop.artgalleryviewerapp.data.remote

import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiServices: ApiServices
) : BaseDataSource() {

    suspend fun getArtworks(page: String) = getResult { apiServices.getArtworks(page) }

}