package com.haryop.artgalleryviewerapp.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.haryop.artgalleryviewerapp.data.helper.Resource
import com.haryop.artgalleryviewerapp.data.model.ArtworkResponseModel
import com.haryop.artgalleryviewerapp.data.remote.RemoteDataSource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class DataRepo
@Inject
constructor(
    private val remoteDataSource: RemoteDataSource,
) {

    fun getArtworks(page: String) = performGetArtworksOperation(
        networkCall = { remoteDataSource.getArtworks(page) }
    )

    fun <A> performGetArtworksOperation(
        networkCall: suspend () -> Resource<A>
    ): LiveData<Resource<ArtworkResponseModel>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                val result = responseStatus.data!! as ArtworkResponseModel
                emit(Resource.success(result))

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
            }
        }

    fun searchArtworks(keyword: String, page: String) = performSearchArtworksOperation(
        networkCall = { remoteDataSource.searchArtworks(keyword, page) }
    )

    fun <A> performSearchArtworksOperation(
        networkCall: suspend () -> Resource<A>
    ): LiveData<Resource<ArtworkResponseModel>> =
        liveData(Dispatchers.IO) {
            emit(Resource.loading())

            val responseStatus = networkCall.invoke()
            if (responseStatus.status == Resource.Status.SUCCESS) {
                val result = responseStatus.data!! as ArtworkResponseModel
                emit(Resource.success(result))

            } else if (responseStatus.status == Resource.Status.ERROR) {
                emit(Resource.error(responseStatus.message!!))
            }
        }

}