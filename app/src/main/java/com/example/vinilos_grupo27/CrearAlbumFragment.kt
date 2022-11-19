package com.example.vinilos_grupo27

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.vinilos_grupo27.databinding.FragmentCrearAlbumBinding
import com.example.vinilos_grupo27.databinding.FragmentFirstBinding


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

        _binding = FragmentCrearAlbumBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_crear_album, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button6.setOnClickListener() {
            Log.d("button_6", "Se dio click en el boton ")
            val name = binding.nameAlbum.text.toString()
            val cover = binding.cover.text.toString()
            val description = binding.descriptionAlbum.text.toString()
            val releaseDate = binding.releaseDate.text.toString()
            val genre = binding.genre.text.toString()
            val recordLabel = binding.recordLabel.text.toString()
            Log.d("button_6", "los valores ingresados son ${name} , ${cover}, ${description}, ${releaseDate}, ${genre}, ${recordLabel}")
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

}