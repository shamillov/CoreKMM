package com.shamilov.androidNative.ui.products.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shamilov.androidNative.R
import com.shamilov.androidNative.ui.products.model.ProductData

/**
 * Created by Shamilov on 17.06.2021
 */
class ProductViewHolder(
    itemView: View,
    private val listener: (ProductData) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    private val ivIcon: ImageView = itemView.findViewById(R.id.iv_icon)
    private val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
    private val tvPrice: TextView = itemView.findViewById(R.id.tv_price)

    fun bind(item: ProductData) {
        ivIcon.load(item.image) { crossfade(500) }
        tvTitle.text = item.name
        tvPrice.text = "${item.price} р."

        itemView.setOnClickListener { listener.invoke(item) }
    }
}