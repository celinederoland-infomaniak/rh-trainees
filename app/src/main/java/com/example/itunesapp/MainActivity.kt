package com.example.itunesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.of
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.itunesapp.network.RetroService
import com.example.itunesapp.network.RetrofitInstance
import com.example.itunesapp.viewmodels.ActivityMainViewModel
import retrofit2.Call
import retrofit2.Response
import java.time.chrono.HijrahEra.of
import java.util.EnumSet.of

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: MainActivityAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        createData()

    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewAdapter = MainActivityAdapter()
        recyclerView.adapter = recyclerViewAdapter

    }

    fun createData() {
        val button = findViewById<Button>(R.id.btSearch)
        val editText = findViewById<EditText>(R.id.etSearch)

        val viewModel = ViewModelProviders.of(this).get(ActivityMainViewModel::class.java)
        viewModel.getRecyclerListDataObserver().observe(this, Observer<ListItunesData> {
            if (it != null) {
                recyclerViewAdapter.setListData(it.results)
                recyclerViewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting data from api", Toast.LENGTH_SHORT).show()
            }
        })

        button.setOnClickListener {
            viewModel.makeApiCall(editText.text.toString())
        }
    }
}
