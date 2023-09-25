package com.haryop.artgalleryviewerapp.data.model

import com.google.gson.annotations.SerializedName

data class ArtworkItemModel(
    @SerializedName("id") val id:String,
    @SerializedName("title") val title:String,
    @SerializedName("artist_display") val artist_display:String,
    @SerializedName("date_display") val date_display:String,
    @SerializedName("main_reference_number") val main_reference_number:String,
    @SerializedName("image_id") val image_id:String,

    var imagePath: String

)
