package com.example.vinilos_grupo27.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Musician
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class MusiciansRepository (val application: Application) {
    suspend fun refreshData(): List<Musician> {
        return NetworkServiceAdapter.getInstance(application).getMusicians()
    }

}