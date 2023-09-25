package com.haryop.artgalleryviewerapp.data.model

import com.google.gson.annotations.SerializedName

data class ArtworkItemModel(
    @SerializedName("id") val id:String,
    @SerializedName("title") val title:String,
    @SerializedName("artist_display") val artistDisplay:String,
    @SerializedName("date_display") val dateDisplay:String,
    @SerializedName("main_reference_number") val mainReferenceNumber:String,
    @SerializedName("image_id") val imageId:String,
)
