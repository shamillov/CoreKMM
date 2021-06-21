package com.shamilov.androidNative.ui.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.shamilov.androidNative.R
import com.shamilov.androidNative.ui.products.model.ProductData

/**
 * Created by Shamilov on 14.06.2021
 */
class ProductFragment : BottomSheetDialogFragment() {

    companion object {
        fun newInstance(bundle: Bundle) =
            ProductFragment().also {
                it.arguments = bundle
            }
    }

    private lateinit var image: ImageView
    private lateinit var title: TextView
    private lateinit var description: TextView
    private lateinit var price: TextView

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.product_fragment, container, false)
        image = view.findViewById(R.id.iv_icon)
        title = view.findViewById(R.id.tv_title)
        description = view.findViewById(R.id.tv_description)
        price = view.findViewById(R.id.tv_price)

        val product = requireArguments()["KEY"] as ProductData

        setData(product)

        return view
    }

    private fun setData(product: ProductData) {
        image.load(product.image)
        title.text = product.name
        description.text = product.description
        price.text = "${product.price} р."
    }

    private fun observeViewModel() {
    }
}