package com.haryop.artgalleryviewerapp.screen.viewmodel

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haryop.artgalleryviewerapp.data.model.ArtworkItemModel
import com.haryop.artgalleryviewerapp.data.model.ArtworkPaginationModel
import com.haryop.artgalleryviewerapp.data.repo.RemoteArtworksRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(private val remoteArtworksRepo: RemoteArtworksRepo) : ViewModel() {

    //::VARIABLES::
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>()
    private val artworks = MutableLiveData<List<ArtworkItemModel>>()
    private val pagination = MutableLiveData<ArtworkPaginationModel>()

    //::GETTERS & SETTERS::
    fun getIsLoading(): LiveData<Boolean> {
        return isLoading
    }

    fun getArtworks(): LiveData<List<ArtworkItemModel>> {
        return artworks
    }

    //::PUBLIC FUNCTION::
    fun init() {
        isLoading.value = true
        getArtworkFromServices()
    }

    //::PRIVATE FUNCTION::
    private fun getArtworkFromServices()=viewModelScope.launch {
        remoteArtworksRepo.getArtworks("1").let { response ->
            if(response.isSuccessful){
                var result = response.body() ?: return@launch

                artworks.postValue(result.data)
                pagination.postValue(result.pagination)

            }else{
                Log.d("tag", "getAllTvShows Error: ${response.code()}")
            }

            isLoading.value = false
        }
    }

}