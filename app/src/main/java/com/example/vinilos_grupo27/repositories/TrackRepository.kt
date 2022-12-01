package com.example.vinilos_grupo27.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.network.NetworkServiceAdapter
import org.json.JSONObject

class TrackRepository(val application: Application) {

    fun postData(albumId:Int,jsonObject: JSONObject, callback: (JSONObject)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).posttrack(albumId,jsonObject,{
            callback(it)
        },
            onError
        )
    }
}