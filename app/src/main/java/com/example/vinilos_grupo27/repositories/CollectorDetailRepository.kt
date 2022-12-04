package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.CollectorDetail
import com.example.vinilos_grupo27.network.CacheManager
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class CollectorDetailRepository(val application: Application) {
    suspend fun refreshData(collectorId: Int): CollectorDetail{
        val potentialResp = CacheManager.getInstance(application.applicationContext).getCollectorDetail()
        if(potentialResp == null){
            val collector = NetworkServiceAdapter.getInstance(application).getCollectorDetail(collectorId)
            CacheManager.getInstance(application.applicationContext).addCollectorDetail(collector)
            return collector
        } else {
            return potentialResp!!
        }

    }
}