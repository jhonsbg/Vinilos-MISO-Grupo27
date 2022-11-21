package com.example.vinilos_grupo27.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.CollectorsItemBinding
import com.example.vinilos_grupo27.models.Collector

class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>(){
    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorsAdapter.CollectorViewHolder {
        val withDataBinding: CollectorsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorsAdapter.CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorsAdapter.CollectorViewHolder(withDataBinding)
    }
    override fun onBindViewHolder(holder: CollectorsAdapter.CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
    }
    override fun getItemCount(): Int {
        return collectors.size
    }

    class CollectorViewHolder(val viewDataBinding: CollectorsItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.collectors_item
        }
    }
}