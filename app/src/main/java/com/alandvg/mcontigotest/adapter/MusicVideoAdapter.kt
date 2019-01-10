package com.alandvg.mcontigotest.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.alandvg.mcontigotest.entity.MusicVideo
import androidx.databinding.DataBindingUtil
import android.view.LayoutInflater
import com.alandvg.mcontigotest.R
import com.alandvg.mcontigotest.databinding.MusicVideoItemBinding
import com.squareup.picasso.Picasso


class MusicVideoAdapter(val listSearch: List<MusicVideo>, val listener: MusicVideoAdapterInterface) :
    RecyclerView.Adapter<MusicVideoAdapter.MusicVideoAdapterViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicVideoAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<MusicVideoItemBinding>(layoutInflater, R.layout.music_video_item, parent, false)
        return MusicVideoAdapterViewHolder(binding)
    }

    override fun getItemCount() = listSearch.size

    override fun onBindViewHolder(holder: MusicVideoAdapterViewHolder, position: Int) {
        holder.bind(listSearch[position], listener)
    }

    class MusicVideoAdapterViewHolder(itemView: MusicVideoItemBinding) : ViewHolder(itemView.root) {

        val binding = itemView

        fun bind(
            musicVideo: MusicVideo,
            listener: MusicVideoAdapterInterface
        ) {
            binding.musicVideo = musicVideo


            Picasso.get().load(musicVideo.artworkUrl100)
                .into(binding.imgAlbum)

            musicVideo.trackTimeMillis?.let {

                val minutes = (it / 1000) / 60
                val seconds = (it / 1000) % 60

                val minutesString = if (minutes.toString().length == 1) "0$minutes" else "$minutes"
                val secondsString = if (seconds.toString().length == 1) "0$seconds" else "$seconds"

                binding.tvTimer.text = "$minutesString:$secondsString"

            }

            musicVideo.artistViewUrl?.let { link ->
                binding.btArtistUrl.setOnClickListener { view ->
                    listener.onSelectDetalhesArtista(link)
                }
            }

        }


    }

}