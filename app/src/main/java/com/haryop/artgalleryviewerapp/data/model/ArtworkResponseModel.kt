package com.haryop.artgalleryviewerapp.data.model

import com.google.gson.annotations.SerializedName

data class ArtworkResponseModel(
    @SerializedName("data") val data:ArrayList<ArtworkItemModel>,
    @SerializedName("pagination") val pagination:ArtworkPaginationModel,
)