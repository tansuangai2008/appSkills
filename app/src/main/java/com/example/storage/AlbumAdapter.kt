package com.example.storage

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemAlbumBinding

/**
 *  author : ly
 *  date : 2020/12/9 14:08
 *  description : 相册适配器
 */
class AlbumAdapter(val context: Context, val imageList: List<Uri>, val imageSize: Int) : RecyclerView.Adapter<AlbumAdapter.ViewHolder>() {

//    constructor(context: Context, imageList: List<Uri>, imageSize: Int)

    private lateinit var binding: ItemAlbumBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemAlbumBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.layoutParams.width = imageSize
        holder.imageView.layoutParams.height = imageSize
        val uri = imageList[position]
        val options = RequestOptions().placeholder(R.drawable.album_loading_bg).override(imageSize, imageSize)
        Glide.with(context).load(uri).apply(options).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    inner class ViewHolder(itemView: ItemAlbumBinding) : RecyclerView.ViewHolder(binding.root) {
       var imageView = itemView.imageView
    }
}