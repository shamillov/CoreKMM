package com.shamilov.androidNative.ui.products.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.androidNative.R
import com.shamilov.androidNative.ui.products.model.ProductData

/**
 * Created by Shamilov on 14.06.2021
 */
class ProductsAdapter(
    private val listener: (ProductData) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    private var items: List<ProductData> = emptyList()

    fun setData(items: List<ProductData>) {
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
}
