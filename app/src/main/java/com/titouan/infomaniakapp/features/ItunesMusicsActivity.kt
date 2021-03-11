package com.titouan.infomaniakapp.features

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.titouan.infomaniakapp.R
import org.koin.android.viewmodel.ext.android.viewModel

class ItunesMusicsActivity : AppCompatActivity() {

    private val viewModel: ItunesMusicsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupSearchButton()
    }

    private fun setupSearchButton() {
        val searchField = findViewById<EditText>(R.id.search_field)
        val searchButton = findViewById<Button>(R.id.search_button)

        searchButton.setOnClickListener {
            viewModel.searchMusics(searchField.text.toString())
        }
    }
}