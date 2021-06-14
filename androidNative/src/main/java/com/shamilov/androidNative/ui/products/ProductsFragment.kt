package com.shamilov.androidNative.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.shamilov.androidNative.R
import com.shamilov.androidNative.ui.products.adapter.ProductsAdapter
import kotlinx.coroutines.flow.collect

/**
 * Created by Shamilov on 14.06.2021
 */
class ProductsFragment : Fragment(R.layout.products_fragment) {

    companion object {
        fun newInstance(bundle: Bundle? = null) = ProductsFragment().also { it.arguments = bundle }
    }

    private val viewModel: ProductsViewModel by viewModels()
    private val adapter by lazy { ProductsAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is ProductsState.Loading -> { }
                    is ProductsState.Success -> { }
                    is ProductsState.Error -> { }
                    is ProductsState.Empty -> { }
                }
            }
        }
    }
}