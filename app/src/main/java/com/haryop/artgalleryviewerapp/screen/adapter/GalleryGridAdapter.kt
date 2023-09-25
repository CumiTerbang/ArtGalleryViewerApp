package com.haryop.artgalleryviewerapp.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.haryop.artgalleryviewerapp.R
import com.haryop.artgalleryviewerapp.data.model.ArtworkItemModel

class GalleryGridAdapter(
    private val artworks: List<ArtworkItemModel>?,
    private val context: Context
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var itemImageView: ImageView

    override fun getCount(): Int {
        return artworks?.size ?: 0
    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        if (count==0) return convertView
        var convertViewResult = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertViewResult == null) {
            convertViewResult = layoutInflater!!.inflate(R.layout.item_grid_gallery, null)
        }

        itemImageView = convertViewResult!!.findViewById(R.id.imageViewGridGaleryItem)
        Glide.with(context).load(artworks?.get(position)?.imagePath).centerCrop().into(itemImageView)

        return convertViewResult
    }
}