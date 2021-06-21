package com.shamilov.androidNative.ui.products

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.androidNative.R
import com.shamilov.androidNative.ui.product.ProductFragment
import com.shamilov.androidNative.ui.products.adapter.ProductsAdapter
import com.shamilov.androidNative.ui.products.mapper.ProductItemMapper
import com.shamilov.androidNative.ui.products.model.ProductData
import com.shamilov.core.remote.KtorClient
import com.shamilov.core.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.collect

/**
 * Created by Shamilov on 14.06.2021
 */
class ProductsFragment : Fragment(R.layout.products_fragment) {

    companion object {
        fun newInstance(bundle: Bundle) =
            ProductsFragment().also {
                it.arguments = bundle
            }
    }

    private lateinit var rvProducts: RecyclerView

    private val viewModel: ProductsViewModel by viewModels {
        ProductsViewModelFactory(requireArguments()["KEY"] as Int)
    }

    private val adapter by lazy {
        ProductsAdapter { product ->
            openProduct(product)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        observeViewModel()
    }

    private fun initRecyclerView() {
        rvProducts = requireView().findViewById(R.id.rv_products)
        rvProducts.setHasFixedSize(true)
        rvProducts.layoutManager = GridLayoutManager(context, 2)
        rvProducts.adapter = adapter
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is ProductsState.Loading -> {

                    }
                    is ProductsState.Success -> {
                        adapter.setData(state.data)
                    }
                    is ProductsState.Error -> {
                        Toast.makeText(context, state.exception.message, Toast.LENGTH_LONG).show()
                    }
                    is ProductsState.Empty -> {

                    }
                }
            }
        }
    }

    private fun openProduct(product: ProductData) {
        ProductFragment.newInstance(bundleOf("KEY" to product)).show(parentFragmentManager, "")
    }
}

class ProductsViewModelFactory(private val id: Int) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProductsViewModelImpl(id, RemoteRepositoryImpl(KtorClient), ProductItemMapper()) as T
    }
}