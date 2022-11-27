package com.example.vinilos_grupo27.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.CollectorDetail
import com.example.vinilos_grupo27.network.NetworkServiceAdapter

class CollectorDetailRepository(val application: Application) {
    fun refreshData(collectorId: Int, callback: (CollectorDetail)->Unit, onError: (VolleyError)->Unit){
        NetworkServiceAdapter.getInstance(application).getCollectorDetail(collectorId, {
            callback(it)
            Log.d("Detalle Collector", "Repository Ok")
        },
            onError
        )
    }
}