package com.example.itunesapp

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MainActivityAdapter : RecyclerView.Adapter<MainActivityAdapter.ViewHolder>() {
    var items = ArrayList<Result>()

    fun setListData(data: ArrayList<Result>) {
        this.items = data
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.tvTitle)
        val tvArtist = view.findViewById<TextView>(R.id.tvArtist)
        val tvCollection = view.findViewById<TextView>(R.id.tvCollection)
        val ivImage = view.findViewById<ImageView>(R.id.ivImageThumb)


        fun bind(data: Result) {
            tvTitle.text = data.trackName
            tvArtist.text = data.artistName
            tvCollection.text = data.collectionName
            val url = data.artworkUrl100
            Glide.with(ivImage)
                .load(url)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .into(ivImage)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainActivityAdapter.ViewHolder {
        val inflater =
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ViewHolder(inflater)
    }

    // Set the value of the recyclerview
    override fun onBindViewHolder(holder: MainActivityAdapter.ViewHolder, position: Int) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int {
        return items.size
    }


}