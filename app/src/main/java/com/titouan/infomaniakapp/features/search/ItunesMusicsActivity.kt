package com.titouan.infomaniakapp.features.search

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.titouan.infomaniakapp.R
import com.titouan.infomaniakapp.features.hideKeyboard
import com.titouan.infomaniakapp.features.utils.Resource
import com.titouan.infomaniakapp.features.utils.isError
import com.titouan.infomaniakapp.features.utils.isLoading
import org.koin.android.viewmodel.ext.android.viewModel

class ItunesMusicsActivity : AppCompatActivity() {

    private val viewModel: ItunesMusicsViewModel by viewModel()

    private val searchField: EditText by lazy { findViewById(R.id.search_field) }
    private val searchButton: Button by lazy { findViewById(R.id.search_button) }
    private val loader: ProgressBar by lazy { findViewById(R.id.loader) }
    private val resultsRecyclerView: RecyclerView by lazy { findViewById(R.id.search_results) }
    private val errorMessage: TextView by lazy { findViewById(R.id.error) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSearchButton()
        setupResultsRecyclerView()
    }

    private fun setupSearchButton() {
        searchButton.setOnClickListener {
            hideKeyboard()
            viewModel.searchMusics(searchField.text.toString())
        }
    }

    private fun setupResultsRecyclerView() {
        val adapter = MusicsSearchResultAdapter()
        resultsRecyclerView.adapter = adapter

        viewModel.results.observe(this) {
            when(it) {
                is Resource.Success -> adapter.setData(it.data)
                is Resource.Error -> adapter.setData(listOf())
            }

            searchButton.isEnabled = !it.isLoading
            loader.isVisible = it.isLoading
            resultsRecyclerView.isVisible = !it.isLoading
            errorMessage.isVisible = it.isError
        }
    }
}