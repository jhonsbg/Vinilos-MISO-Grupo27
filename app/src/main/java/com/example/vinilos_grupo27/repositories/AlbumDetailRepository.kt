package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.example.vinilos_grupo27.models.AlbumDetail
import com.example.vinilos_grupo27.network.CacheManager
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class AlbumDetailRepository (val application: Application) {
    suspend fun refreshData(albumId: Int): AlbumDetail {
        Log.d("potentialResp", "Inicializando cache albumdetail")
        val potentialResp = CacheManager.getInstance(application.applicationContext).getAlbumDetail()
        Log.d("potentialResp", "Instance ok cache albumdetail")
        Log.d("potentialResp", "VAlor potentialResp ${potentialResp}")
        if (potentialResp == null) {
            Log.d("potentialResp", "Estoy por acá")
            val album = NetworkServiceAdapter.getInstance(application).getAlbumDetail(albumId)
            Log.d("potentialResp", "Valor album ${album}")
            CacheManager.getInstance(application.applicationContext).addAlbumDetail(album)
            Log.d("potentialResp", "Estoy por acá Cache")
            return album
        }
        else{
            Log.d("potentialResp", potentialResp.toString())
            return potentialResp!!
        }
    }
}