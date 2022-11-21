package com.example.vinilos_grupo27.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.AlbumDetail
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application) {
    fun refreshData(albumId: Int, callback: (AlbumDetail)->Unit, onError: (VolleyError)->Unit){
        NetworkServiceAdapter.getInstance(application).getAlbumDetail(albumId, {
                callback(it)
            },
            onError
        )
    }
}