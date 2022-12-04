package com.example.vinilos_grupo27.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.vinilos_grupo27.R
import com.example.vinilos_grupo27.databinding.FragmentArtistDetailBinding
import com.example.vinilos_grupo27.models.ArtistDetail
import com.example.vinilos_grupo27.ui.adapters.ArtistDetailAdapter
import com.example.vinilos_grupo27.viewmodel.ArtistDetailViewModel

class ArtistDetailFragment : Fragment() {

    private var _binding: FragmentArtistDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewModel: ArtistDetailViewModel
    private var viewModelAdapter: ArtistDetailAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentArtistDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        viewModelAdapter = ArtistDetailAdapter()
        Log.d("onCreateView", "Aquí")
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = binding.artistdetailRv
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = viewModelAdapter
        Log.d("onViewCreated", "Aquí")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_artistDetail)
        val args: ArtistDetailFragmentArgs by navArgs()
        Log.d("Args", args.albumId.toString())
        Log.d("Variables Fragmento", this.toString())
        viewModel = ViewModelProvider(this, ArtistDetailViewModel.Factory(activity.application, args.albumId)).get(
            ArtistDetailViewModel::class.java)
        Log.d("Artist Detail Fragmento", viewModel.toString())
        viewModel.artistDetail.observe(viewLifecycleOwner, Observer<ArtistDetail> {
            it.apply {
                viewModelAdapter!!.artistDetails = listOf(this)
                if(this.artistId == null){
                    binding.txtNoComments.visibility = View.VISIBLE
                }else{
                    binding.txtNoComments.visibility = View.GONE
                }
            }
        })
        Log.d("Fragmento despues", "2")
        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean> { isNetworkError ->
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