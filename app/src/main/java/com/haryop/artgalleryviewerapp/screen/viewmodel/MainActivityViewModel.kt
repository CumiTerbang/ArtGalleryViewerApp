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

    private val _page = MutableLiveData<String>()
    private val _searchMap = MutableLiveData<Map<String, String>>()

    private val _getArtworkResponse = _page.switchMap { page ->
        dataRepo.getArtworks(page)
    }
    val getArtworkResponse: LiveData<Resource<ArtworkResponseModel>> = _getArtworkResponse

    private val _searchArtworkResponse = _searchMap.switchMap { map ->
        dataRepo.searchArtworks(map["keyword"] ?: "", map["page"] ?: "")
    }
    val searchArtworkResponse: LiveData<Resource<ArtworkResponseModel>> = _searchArtworkResponse

    fun init() {
        _page.value = "1"
    }

    fun search(keyword: String, page: String) {
        _searchMap.value = mapOf("keyword" to keyword, "page" to page)
    }

    fun setPage(page: String) {
        _page.value = page
    }


}