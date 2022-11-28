package com.example.vinilos_grupo27

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.vinilos_grupo27.databinding.FragmentCrearAlbumBinding
import com.example.vinilos_grupo27.databinding.FragmentCrearTrackBinding
import com.example.vinilos_grupo27.models.Album
import com.example.vinilos_grupo27.models.TrackNoId
import com.example.vinilos_grupo27.ui.AlbumDetailFragmentArgs
import com.example.vinilos_grupo27.viewmodel.AddTrackViewModel
import com.example.vinilos_grupo27.viewmodel.AlbumDetailViewModel
import com.google.gson.Gson
import org.json.JSONObject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CrearTrackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CrearTrackFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentCrearTrackBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel : AddTrackViewModel
    private var idAlbum : Int = 9

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCrearTrackBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_crear_track, container, false)
    }

    // codigo Nelson

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)
        val args: CrearTrackFragmentArgs by navArgs()
        Log.d("Variables Fragmento", this.toString())
        //Log.d("Args2", args.albumId.toString())
        //idAlbum = args.albumId

        binding.buttonTrack.setOnClickListener() {
            Log.d("button_track", "Se dio click en el boton ")
            val name = binding.nameTrack.text.toString()
            val minutos = binding.durationTrackMinutos.text.toString()
            val segundos = binding.durationTrackSegundos.text.toString()
            val duration = "${minutos}:${segundos}"
            Log.d(
                "button_track",
                "los valores ingresados son ${name} , con una duracion de  ${duration}"
            )

            val track: TrackNoId = TrackNoId(
                name = name,
                duration = duration
            )

            Log.d(
                "button_track",
                "la clase es ${track.name} con tiempo de duracion ${track.duration}"
            )
            //val args: CrearTrackFragmentArgs by navArgs()
            Log.d("button_track", "hola, track")
            //Log.d("button_track", "El id del Album es ${idAlbum}")
            viewModel = ViewModelProvider(this, AddTrackViewModel.Factory(activity.application, idAlbum)).get(
                AddTrackViewModel::class.java)
            viewModel.createTrack(track,idAlbum)
        }
    }

    // fin codigo Nelson
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CrearTrackFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CrearTrackFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
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