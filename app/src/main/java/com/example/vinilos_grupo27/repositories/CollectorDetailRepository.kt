package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.CollectorDetail
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class CollectorDetailRepository(val application: Application) {
    suspend fun refreshData(collectorId: Int): CollectorDetail{
        return NetworkServiceAdapter.getInstance(application).getCollectorDetail(collectorId)

    }
}