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
import com.shamilov.core.model.response.ProductResponse

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

    private val viewModel: ProductViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.product_fragment, container, false)
    }
}