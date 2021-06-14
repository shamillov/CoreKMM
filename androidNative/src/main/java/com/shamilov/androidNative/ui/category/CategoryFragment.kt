package com.shamilov.androidNative.ui.category

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.androidNative.R
import com.shamilov.androidNative.ui.category.adapter.CategoryAdapter
import com.shamilov.androidNative.ui.products.ProductsFragment
import com.shamilov.androidNative.utils.extensions.gone
import com.shamilov.androidNative.utils.extensions.visible
import com.shamilov.core.remote.KtorClient
import com.shamilov.core.repository.RemoteRepositoryImpl
import kotlinx.coroutines.flow.collect

/**
 * Created by Shamilov on 24.05.2021
 */
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val viewModel: CategoryViewModel by viewModels { ViewModelFactory() }

    private val adapter = CategoryAdapter { id ->
        openProducts(id)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val progressBar = view.findViewById<ProgressBar>(R.id.progress_bar)
        val rvCategory = view.findViewById<RecyclerView>(R.id.rv_category)

        rvCategory.layoutManager = LinearLayoutManager(requireContext())
        rvCategory.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when (state) {
                    is CategoryState.Loading -> {
                        progressBar.visible()
                    }
                    is CategoryState.Success -> {
                        progressBar.gone()
                        adapter.setData(state.data)
                    }
                    is CategoryState.Error -> {
                        Toast.makeText(requireContext(), state.exception.message, Toast.LENGTH_SHORT).show()
                    }
                    is CategoryState.IsEmpty -> {
                        Toast.makeText(requireContext(), "Categories is empty", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun openProducts(id: Int) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ProductsFragment.newInstance(bundleOf("KEY" to id)))
            .addToBackStack("")
            .commit()
    }
}

class ViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CategoryViewModel(RemoteRepositoryImpl(KtorClient)) as T
    }
}