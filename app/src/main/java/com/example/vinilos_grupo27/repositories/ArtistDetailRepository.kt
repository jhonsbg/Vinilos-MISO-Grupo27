package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.ArtistDetail
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class ArtistDetailRepository (val application: Application) {
    fun refreshData(albumId: Int, callback: (ArtistDetail)->Unit, onError: (VolleyError)->Unit){
        NetworkServiceAdapter.getInstance(application).getArtistDetail(albumId, {
            callback(it)
            Log.d("Detalle", "Repository Ok")
        },
            onError
        )
    }
}