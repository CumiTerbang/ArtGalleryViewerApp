package com.haryop.artgalleryviewerapp.data.helper

object Constants {
    const val BASE_URL = "https://api.artic.edu/api/v1/"
    const val GET_ARTWORKS = "fields=id,title,artist_display,date_display,main_reference_number,image_id"
    const val PAGINATION_LIMIT = "limit=45"

    fun getImagePath(imageId: String?): String {
        return "https://www.artic.edu/iiif/2/$imageId/full/200,/0/default.jpg"
    }
}