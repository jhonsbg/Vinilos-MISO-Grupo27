package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.ArtistDetail
import com.example.vinilos_grupo27.network.CacheManager
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class ArtistDetailRepository (val application: Application) {
    suspend fun refreshData(artistId: Int): ArtistDetail {
        val potentialResp = CacheManager.getInstance(application.applicationContext).getArtistDetail()
        if (potentialResp == null) {
            val artist = NetworkServiceAdapter.getInstance(application).getArtistDetail(artistId)
            CacheManager.getInstance(application.applicationContext).addArtistDetail(artist)
            return artist
        }
        else{
        return potentialResp!!
        }
    }
}