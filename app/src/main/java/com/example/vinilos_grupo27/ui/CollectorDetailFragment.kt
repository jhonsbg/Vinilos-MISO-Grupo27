package com.example.vinilos_grupo27.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.FragmentCollectorDetailBinding
import com.example.vinilos_grupo27.models.CollectorDetail
import com.example.vinilos_grupo27.ui.adapters.CollectorDetailAdapter
import com.example.vinilos_grupo27.viewmodel.CollectorDetailViewModel

class CollectorDetailFragment : Fragment() {

    private var _binding: FragmentCollectorDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: CollectorDetailViewModel
    private var viewModelAdapter: CollectorDetailAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCollectorDetailBinding.inflate(inflater,container,false)
        val view = binding.root
        viewModelAdapter = CollectorDetailAdapter()
        Log.d("onCreateView", "Ok")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.collectordetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        Log.d("onViewCreated", "Ok")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity){
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_collectorDetail)
        val args: CollectorDetailFragmentArgs by navArgs()
        viewModel = ViewModelProvider(this,CollectorDetailViewModel.Factory(activity.application, args.collectorId)).get(
            CollectorDetailViewModel::class.java)
        viewModel.collectorDetail.observe(viewLifecycleOwner, Observer<CollectorDetail>{
            it.apply {
                viewModelAdapter!!.collectorDetails = listOf(this)
                if(this.collectorId ==null){
                    binding.txtNoComments.visibility = View.VISIBLE
                }else{
                    binding.txtNoComments.visibility = View.GONE
                }
            }
        })
        viewModel.eventNetworkError.observe(viewLifecycleOwner,Observer<Boolean>{ isNetworkError ->
            if (isNetworkError) onNetworkError()
        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onNetworkError() {
        if(!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}