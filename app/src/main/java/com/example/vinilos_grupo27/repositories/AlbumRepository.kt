package com.example.vinilos_grupo27.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Album
import com.example.vinilos_grupo27.network.NetworkServiceAdapter


class AlbumRepository(val application: Application) {
    suspend fun refreshData():List<Album> {
        return NetworkServiceAdapter.getInstance(application).getAlbums()
    }
}