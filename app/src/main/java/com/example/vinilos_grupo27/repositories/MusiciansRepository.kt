package com.example.vinilos_grupo27.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Musician
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class MusiciansRepository (val application: Application) {

    fun refreshData(callback: (List<Musician>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getMusicians({
            callback(it)
        },
            onError
        )
    }

}