package com.alandvg.mcontigotest.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alandvg.mcontigotest.entity.MusicVideo
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.alandvg.mcontigotest.R
import com.alandvg.mcontigotest.databinding.MusicVideoItemBinding
import com.squareup.picasso.Picasso


class MusicVideoAdapter(val listSearch: List<MusicVideo>) :
    RecyclerView.Adapter<MusicVideoAdapter.MusicVideoAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicVideoAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<MusicVideoItemBinding>(layoutInflater, R.layout.music_video_item, parent, false)
        return MusicVideoAdapterViewHolder(binding)
    }

    override fun getItemCount() = listSearch.size

    override fun onBindViewHolder(holder: MusicVideoAdapterViewHolder, position: Int) {
        holder.bind(listSearch[position])
    }

    class MusicVideoAdapterViewHolder(itemView: MusicVideoItemBinding) : ViewHolder(itemView.root) {

        val binding = itemView

        fun bind(musicVideo : MusicVideo){
            binding.musicVideo = musicVideo


           Picasso.get().load(musicVideo.artworkUrl100)
               .into(binding.imgAlbum)

        }


    }

}