package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.AlbumDetail
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application) {
    suspend fun refreshData(albumId: Int): AlbumDetail {
        return NetworkServiceAdapter.getInstance(application).getAlbumDetail(albumId)
    }}