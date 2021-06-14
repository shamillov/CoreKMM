package com.shamilov.androidNative.ui.products.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.androidNative.R
import com.shamilov.core.model.response.ProductResponse

/**
 * Created by Shamilov on 14.06.2021
 */
class ProductsAdapter() : RecyclerView.Adapter<ProductsAdapter.ProductViewHolder>() {

    private var items: List<ProductResponse> = emptyList()

    fun setData(items: List<ProductResponse>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()

    inner class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ProductResponse) {

        }
    }
}
