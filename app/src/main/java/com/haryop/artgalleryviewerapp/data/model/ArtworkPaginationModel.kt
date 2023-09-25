package com.haryop.artgalleryviewerapp.data.model

import com.google.gson.annotations.SerializedName

data class ArtworkPaginationModel(
    @SerializedName("total") val total:Int,
    @SerializedName("limit") val limit:Int,
    @SerializedName("offset") val offset:Int,
    @SerializedName("total_pages") val totalPages:Int,
    @SerializedName("current_page") val currentPage:Int,
    @SerializedName("prev_url") val prevUrl:String,
    @SerializedName("next_url") val nextUrl:String,
)
