package com.example.vinilos_grupo27.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.AlbumDetailItemBinding
import com.example.vinilos_grupo27.models.AlbumDetail

class AlbumDetailAdapter: RecyclerView.Adapter<AlbumDetailAdapter.AlbumDetailViewHolder>() {

    var albumDetails : List<AlbumDetail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumDetailViewHolder {
        val withDataBinding: AlbumDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            AlbumDetailViewHolder.LAYOUT,
            parent,
            false)
        Log.d("AdapterOnCreateViewHolder", "Ok")
        return AlbumDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: AlbumDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {

            it.albumDetail = albumDetails[position]
            Log.d("AdapterOnBindViewHolder", "Ok")

        }
    }

    override fun getItemCount(): Int {
        return albumDetails.size
        Log.d("Adap GerItemCount", "Ok")

    }

    class AlbumDetailViewHolder(val viewDataBinding: AlbumDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.album_detail_item

        }

    }
}