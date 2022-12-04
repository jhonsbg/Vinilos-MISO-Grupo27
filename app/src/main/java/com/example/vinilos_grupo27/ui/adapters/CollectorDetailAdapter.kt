package com.example.vinilos_grupo27.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.CollectorDetailItemBinding
import com.example.vinilos_grupo27.models.CollectorDetail
class CollectorDetailAdapter: RecyclerView.Adapter<CollectorDetailAdapter.CollectorDetailViewHolder>() {

    var collectorDetails : List<CollectorDetail> = emptyList()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorDetailViewHolder {
        val withDataBinding: CollectorDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorDetailViewHolder.LAYOUT,
            parent,
            false)
        return CollectorDetailViewHolder((withDataBinding))
    }

    override fun onBindViewHolder(holder: CollectorDetailViewHolder, position: Int) {

        holder.viewDataBinding.also {
            it.collectorDetail = collectorDetails[position]
            Log.d("AdapterOnBindViewHolder Collector", "Ok")
        }
    }

    override fun getItemCount(): Int {
        return collectorDetails.size
        Log.d("Adap GerItemCount Collector", "Ok")
    }


    class CollectorDetailViewHolder(val viewDataBinding: CollectorDetailItemBinding):
            RecyclerView.ViewHolder(viewDataBinding.root){
                companion object{
                    @LayoutRes
                    val LAYOUT = R.layout.collector_detail_item
                }
            }
}