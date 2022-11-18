package com.example.vinilos_grupo27.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Album
import com.example.vinilos_grupo27.network.NetworkServiceAdapter
import org.json.JSONObject


class AlbumRepository(val application: Application) {
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
            onError
        )
    }
    fun postData(jsonObject: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).postalbumm(jsonObject,{
            callback(it)
        },
            onError
        )
    }




}