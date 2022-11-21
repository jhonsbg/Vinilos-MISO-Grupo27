package com.example.vinilos_grupo27.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.example.vinilos_grupo27.models.Collector
import com.example.vinilos_grupo27.network.NetworkServiceAdapter


class CollectorsRepository(val application: Application)  {
    fun refreshData(callback: (List<Collector>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getCollectors({
            callback(it)
        },
            onError
        )
    }
}