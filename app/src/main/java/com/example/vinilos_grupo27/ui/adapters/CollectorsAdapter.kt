package com.example.vinilos_grupo27.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.CollectorsItemBinding
import com.example.vinilos_grupo27.models.Collector
import com.example.vinilos_grupo27.ui.CollectorsFragmentDirections

class CollectorsAdapter : RecyclerView.Adapter<CollectorsAdapter.CollectorViewHolder>(){
    var collectors :List<Collector> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var collector : Collector = Collector(0,"")
        set(value) {
            field = value
            notifyDataSetChanged()
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: CollectorsItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }
    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.collector = collectors[position]
        }
        holder.viewDataBinding.root.setOnClickListener{
            val action = CollectorsFragmentDirections.actionCollectorFragmentToCollectorDetailFragment(collectors[position].collectoId)
            holder.viewDataBinding.root.findNavController().navigate(action)
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