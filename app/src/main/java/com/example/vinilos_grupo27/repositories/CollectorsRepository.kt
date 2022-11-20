package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Collector
import com.example.vinilos_grupo27.network.CacheManager
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class CollectorsRepository(val application: Application)  {
    suspend fun refreshData(): List<Collector> {
        val potentialResp = CacheManager.getInstance(application.applicationContext).getCollector()
        if (potentialResp.isEmpty()) {
            val collectors = NetworkServiceAdapter.getInstance(application).getCollectors()
            CacheManager.getInstance(application.applicationContext).addCollector(collectors)
            return collectors
        }
        else{
            Log.d("potentialResp", potentialResp.toString())
            return potentialResp!!
        }



    }
}