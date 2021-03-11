package com.titouan.infomaniakapp.features

import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.titouan.infomaniakapp.R
import com.titouan.infomaniakapp.data.models.Music

class MusicsSearchResultAdapter :
    RecyclerView.Adapter<MusicsSearchResultAdapter.MusicViewHolder>() {

    private val items: MutableList<Music> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder =
        MusicViewHolder(parent)

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(data: List<Music>) {
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    class MusicViewHolder(
        parent: ViewGroup
    ) : RecyclerView.ViewHolder(parent.inflate(R.layout.item_music)) {

        fun bind(music: Music) {
            itemView.findViewById<TextView>(R.id.title).text = music.trackName
            itemView.findViewById<TextView>(R.id.artist).text = music.artistName
            itemView.findViewById<TextView>(R.id.album).text = music.collectionName
        }
    }
}