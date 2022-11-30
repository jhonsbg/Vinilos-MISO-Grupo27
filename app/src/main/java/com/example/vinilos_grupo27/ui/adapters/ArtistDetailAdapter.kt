package com.example.vinilos_grupo27.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.ArtistDetailItemBinding
import com.example.vinilos_grupo27.models.ArtistDetail

class ArtistDetailAdapter: RecyclerView.Adapter<ArtistDetailAdapter.ArtistDetailViewHolder>() {

    var artistDetails : List<ArtistDetail> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtistDetailAdapter.ArtistDetailViewHolder {
        val withDataBinding: ArtistDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            ArtistDetailAdapter.ArtistDetailViewHolder.LAYOUT,
            parent,
            false)
        Log.d("AdapterOnCreateViewHolder", "Ok")
        return ArtistDetailAdapter.ArtistDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: ArtistDetailAdapter.ArtistDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {

            it.artistDetail = artistDetails[position]
            Log.d("AdapterOnBindViewHolder", "Ok")

        }
    }

    override fun getItemCount(): Int {
        return artistDetails.size
        Log.d("Adap GerItemCount", "Ok")

    }

    class ArtistDetailViewHolder(val viewDataBinding: ArtistDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.artist_detail_item

        }
    }

}