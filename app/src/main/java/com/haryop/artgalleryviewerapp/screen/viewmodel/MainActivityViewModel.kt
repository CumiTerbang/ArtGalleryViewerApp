package com.haryop.artgalleryviewerapp.screen.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.haryop.artgalleryviewerapp.data.helper.Resource
import com.haryop.artgalleryviewerapp.data.model.ArtworkResponseModel
import com.haryop.artgalleryviewerapp.data.repo.DataRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel
@Inject
constructor(private val dataRepo: DataRepo) : ViewModel() {

    //VARIABLES
    private val _page = MutableLiveData<String>()
    private val _getArtworkResponse = _page.switchMap { source ->
        dataRepo.getArtworks(source)
    }
    val getArtworkResponse: LiveData<Resource<ArtworkResponseModel>> = _getArtworkResponse

    //GETTERS & SETTERS

    //PUBLIC FUNCTION
    fun init() {
        _page.value = "1"
    }

    //PRIVATE FUNCTION

}