package com.example.vinilos_grupo27

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.vinilos_grupo27.databinding.FragmentCrearAlbumBinding
import com.example.vinilos_grupo27.databinding.FragmentFirstBinding
import com.example.vinilos_grupo27.models.Album
import com.example.vinilos_grupo27.repositories.AlbumRepository
import com.example.vinilos_grupo27.ui.adapters.AlbumsAdapter
import com.example.vinilos_grupo27.viewmodel.AddAlbumViewModel
import com.example.vinilos_grupo27.viewmodel.AlbumViewModel
import com.google.gson.Gson
import org.json.JSONObject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [CrearAlbumFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CrearAlbumFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var _binding: FragmentCrearAlbumBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: AddAlbumViewModel
    private var viewModelAdapter: AlbumsAdapter? = null
    private val viewModel2 : AddAlbumViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
        viewModelAdapter = AlbumsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCrearAlbumBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_crear_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onActivityCreated()"
        }
        activity.actionBar?.title = getString(R.string.title_albums)

        binding.button6.setOnClickListener() {
            Log.d("button_6", "Se dio click en el boton ")
            val name1 = binding.nameAlbum.text.toString()
            val cover1 = binding.cover.text.toString()
            val description1 = binding.descriptionAlbum.text.toString()
            val releaseDate1 = binding.releaseDate.text.toString()
            val genre1 = binding.genre.text.toString()
            val recordLabel1 = binding.recordLabel.text.toString()
            Log.d(
                "button_6",
                "los valores ingresados son ${name1} , ${cover1}, ${description1}, ${releaseDate1}, ${genre1}, ${recordLabel1}"
            )

            val album: Album = Album(
                albumId = 0,
                name = name1,
                cover = cover1,
                releaseDate = releaseDate1,
                description = description1,
                genre = genre1,
                recordLabel = recordLabel1
            )
            Log.d(
                "button_6",
                "la clase es ${album.albumId} con descripcion ${album.description}"
            )

            var gson = Gson()
            var jsonString = gson.toJson(album)
            val jsonAlbum = JSONObject(jsonString)
            Log.d(
                "button_6",
                "los valores en JSON ingresados son ${jsonString}"
            )
            viewModel2.createAlbum(album)
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CrearAlbumFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CrearAlbumFragment().apply {
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