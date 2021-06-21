package com.shamilov.androidNative.ui.category.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.shamilov.androidNative.R
import com.shamilov.core.model.response.CategoryResponse

/**
 * Created by Shamilov on 25.05.2021
 */
class CategoryAdapter(
    private val listener: (Int) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private var items = emptyList<CategoryResponse>()

    fun setData(items: List<CategoryResponse>) {
        this.items = items
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_category, parent, false),
            listener
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.count()
}