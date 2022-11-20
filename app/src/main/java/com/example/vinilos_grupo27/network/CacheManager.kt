package com.example.vinilos_grupo27.network

import android.content.Context
import android.util.Log
import com.example.vinilos_grupo27.models.Album
import com.example.vinilos_grupo27.models.Collector
import com.example.vinilos_grupo27.models.Musician

class CacheManager(context: Context){
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
        instance ?: synchronized(this) {
            instance ?: CacheManager(context).also {
                instance = it
            }
        }
    }
    private var albums:List<Album> = ArrayList<Album>()
    private var collectors:List<Collector> = ArrayList<Collector>()
    private var musician:List<Musician> = ArrayList<Musician>()

    fun addAlbum(albumes :List<Album>){
        for (item: Album in albumes) {
            Log.d("addAlbum", item.toString())
            albums.toMutableList().add(item)
        }
    }
    fun getAlbum(): List<Album> {
        Log.d("getAlbum", albums.toString())
        return albums

    }

    fun addCollector(collectors :List<Collector>){
        for (item: Collector in collectors) {
            Log.d("addCollector", item.toString())
            collectors.toMutableList().add(item)
        }
    }
    fun getCollector(): List<Collector> {
        Log.d("getCollector", collectors.toString())
        return collectors

    }

    fun addMusician(musicians :List<Musician>){
        for (item: Musician in musicians) {
            Log.d("addMusician", item.toString())
            musician.toMutableList().add(item)
        }
    }
    fun getMusician(): List<Musician> {
        Log.d("getMusician", musician.toString())
        return musician

    }
}