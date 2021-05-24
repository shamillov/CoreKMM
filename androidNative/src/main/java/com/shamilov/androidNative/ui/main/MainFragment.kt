package com.shamilov.androidNative.ui.main

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.shamilov.androidNative.R
import com.shamilov.core.repository.RemoteRepositoryImpl

class MainFragment : Fragment(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textView = view.findViewById<TextView>(R.id.textView)

        val repository = RemoteRepositoryImpl()

        repository.loadCategories { result ->
            when {
                result.isSuccess -> textView.text = result.getOrNull()?.data.toString()
                result.isFailure -> textView.text = result.exceptionOrNull()?.message
            }
        }
    }
}