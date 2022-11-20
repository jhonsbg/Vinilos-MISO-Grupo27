package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Musician
import com.example.vinilos_grupo27.network.CacheManager
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class MusiciansRepository (val application: Application) {
    suspend fun refreshData(): List<Musician> {
        val potentialResp = CacheManager.getInstance(application.applicationContext).getMusician()
        if (potentialResp.isEmpty()) {
            val musicians = NetworkServiceAdapter.getInstance(application).getMusicians()
            CacheManager.getInstance(application.applicationContext).addMusician(musicians)
            return musicians
        } else {
            Log.d("potentialResp", potentialResp.toString())
            return potentialResp!!
        }
    }
}