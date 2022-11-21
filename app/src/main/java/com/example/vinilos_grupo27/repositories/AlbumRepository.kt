package com.example.vinilos_grupo27.repositories

import android.app.Application

import android.util.Log

import com.example.vinilos_grupo27.network.CacheManager
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Album
import com.example.vinilos_grupo27.network.NetworkServiceAdapter
import org.json.JSONObject


class AlbumRepository(val application: Application) {
    suspend fun refreshData():List<Album> {

        val potentialResp = CacheManager.getInstance(application.applicationContext).getAlbum()
        if (potentialResp.isEmpty()) {
            val albums = NetworkServiceAdapter.getInstance(application).getAlbums()
            Log.d("musicians", albums.toString())
            CacheManager.getInstance(application.applicationContext).addAlbum(albums)
            return albums
        }
        else{
            Log.d("potentialResp", potentialResp.toString())
            return potentialResp!!
        }}



    fun postData(jsonObject: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).postalbumm(jsonObject,{
            callback(it)
        },
            onError
        )
    }
    
}

