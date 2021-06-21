package com.shamilov.androidNative.ui.category.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shamilov.androidNative.R
import com.shamilov.core.model.response.CategoryResponse

/**
 * Created by Shamilov on 16.06.2021
 */
class CategoryViewHolder(
    itemView: View,
    private val listener: (Int) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val image: ImageView = itemView.findViewById(R.id.iv_category)
    private val title: TextView = itemView.findViewById(R.id.tv_category)

    fun bind(model: CategoryResponse) {
        image.load(model.image.url) { crossfade(500) }
        title.text = model.name

        itemView.setOnClickListener { listener(model.id) }
    }
}