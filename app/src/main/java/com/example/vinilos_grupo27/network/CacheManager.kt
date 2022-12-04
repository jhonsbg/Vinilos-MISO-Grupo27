package com.example.vinilos_grupo27.network

import android.content.Context
import android.util.Log
import com.example.vinilos_grupo27.models.*

class CacheManager(context: Context){
    companion object {
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
    private var albumDetail:List<AlbumDetail> = ArrayList<AlbumDetail>()
    //private val albumDetail:AlbumDetail= AlbumDetail()
    private var collectorDetail:List<CollectorDetail> = ArrayList<CollectorDetail>()
    private var artistDetail:List<ArtistDetail> = ArrayList<ArtistDetail>()



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

    fun getAlbumDetail(): AlbumDetail? {
        Log.d("potentialResp", "Inicializando getAlbumDetail")
        Log.d("potentialResp", "Revisando albumDetail ${albumDetail}")
        Log.d("potentialResp", "Mirando si el album está vacío ${albumDetail.isEmpty()}")

        if(albumDetail.isEmpty()){
            return null


        }
        return albumDetail.get(0)

    }
    fun addAlbumDetail(albumDetail2: AlbumDetail){
        albumDetail.toMutableList().add(albumDetail2)
    }


    fun addArtistDetail(artistDetail2: ArtistDetail){
        artistDetail.toMutableList().add(artistDetail2)

    }


    fun getCollectorDetail():CollectorDetail?{
        if(collectorDetail.isEmpty()){
            return null
        }
        return collectorDetail.get(0)
    }

    fun addCollectorDetail(collectorDetail2:CollectorDetail){
        collectorDetail.toMutableList().add(collectorDetail2)
    }




    fun getArtistDetail():ArtistDetail?{
        if(artistDetail.isEmpty()){
            return null
        }
        return artistDetail.get(0)
    }
}