package com.shamilov.androidNative.ui.category

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shamilov.androidNative.R
import com.shamilov.androidNative.ui.category.adapter.CategoryAdapter
import kotlinx.coroutines.flow.collect

/**
 * Created by Shamilov on 24.05.2021
 */
class CategoryFragment : Fragment(R.layout.fragment_category) {

    private val viewModel: CategoryViewModel by viewModels()

    private val adapter = CategoryAdapter()

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
                        progressBar.visibility = View.VISIBLE
                    }
                    is CategoryState.Success -> {
                        progressBar.visibility = View.GONE
                        adapter.setData(state.data)
                    }
                    is CategoryState.Error -> {
                        Toast.makeText(requireContext(), state.exception?.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}