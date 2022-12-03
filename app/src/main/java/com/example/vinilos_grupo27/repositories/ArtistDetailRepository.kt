package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.ArtistDetail
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class ArtistDetailRepository (val application: Application) {
    suspend fun refreshData(albumId: Int): ArtistDetail {
        return NetworkServiceAdapter.getInstance(application).getArtistDetail(albumId)
    }
}