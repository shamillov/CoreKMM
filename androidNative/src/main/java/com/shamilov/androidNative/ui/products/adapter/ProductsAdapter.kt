package com.shamilov.androidNative.ui.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shamilov.androidNative.R
import com.shamilov.core.model.response.ProductResponse

/**
 * Created by Shamilov on 14.06.2021
 */
class ProductsAdapter(
    private val listener: (ProductResponse) -> Unit
) : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var items: List<ProductResponse> = emptyList()

    fun setData(items: List<ProductResponse>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false),
        listener
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    inner class ProductViewHolder(
        itemView: View,
        private val listener: (ProductResponse) -> Unit
    ) : RecyclerView.ViewHolder(itemView) {

        private val ivIcon = itemView.findViewById<ImageView>(R.id.iv_icon)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)

        fun bind(item: ProductResponse) {
            ivIcon.load(item.image?.getOrNull(0)?.url) { crossfade(1000) }
            tvTitle.text = item.name
            itemView.setOnClickListener { listener.invoke(item) }
        }
    }
}
