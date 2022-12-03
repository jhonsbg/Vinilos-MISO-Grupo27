package com.example.vinilos_grupo27.network

import android.content.Context
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import com.android.volley.VolleyError


import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.vinilos_grupo27.models.*
import com.example.vinilos_grupo27.network.NetworkServiceAdapter.Companion.BASE_URL
import org.json.JSONArray
import org.json.JSONObject


class NetworkServiceAdapter constructor(context: Context) {

    companion object{
        const val BASE_URL= "https://vynils-back2022.herokuapp.com/"
        var instance: NetworkServiceAdapter? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: NetworkServiceAdapter(context).also {
                    instance = it
                }
            }
    }

    private val requestQueue: RequestQueue by lazy {
        Volley.newRequestQueue(context.applicationContext)
    }

    private fun getRequest(path:String, responseListener: Response.Listener<String>, errorListener: Response.ErrorListener): StringRequest {
        return StringRequest(Request.Method.GET, BASE_URL+path, responseListener,errorListener)

    }
    suspend fun getAlbums()=suspendCoroutine<List<Album>>{ cont ->
        requestQueue.add(getRequest("albums",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Album>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Album(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                }
                cont.resume(list)
                Log.d("albums", list.toString())
            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))

    }
    
   
    
    suspend fun getMusicians()= suspendCoroutine<List<Musician>>{cont->
        requestQueue.add(getRequest("musicians",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Musician>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Musician(albumId = item.getInt("id"), name = item.getString("name")))
                }
                cont.resume(list)
                Log.d("musicos", list.toString())

            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))

    }
    suspend fun getCollectors()=suspendCoroutine<List<Collector>>{cont->
        requestQueue.add(getRequest("collectors",
            { response ->
                val resp = JSONArray(response)
                val list = mutableListOf<Collector>()
                for (i in 0 until resp.length()) {
                    val item = resp.getJSONObject(i)
                    list.add(i, Collector(collectoId = item.getInt("id"),name = item.getString("name")))
                }
                cont.resume(list)

            },
            Response.ErrorListener {
                cont.resumeWithException(it)
            }))
    }
  
    

    private fun postRequest(path: String, body: JSONObject,  responseListener: Response.Listener<JSONObject>, errorListener: Response.ErrorListener ): JsonObjectRequest {
        return  JsonObjectRequest(Request.Method.POST, BASE_URL+path, body, responseListener, errorListener)
    }
    fun postalbumm(body: JSONObject, onComplete:(resp: JSONObject)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums",
            body,
            Response.Listener<JSONObject> { response ->
                onComplete(response)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }


    suspend fun getAlbumDetail(albumId:Int)= suspendCoroutine<AlbumDetail>{cont ->
        requestQueue.add(
            getRequest("albums/$albumId",
                Response.Listener<String> { response ->
                    val resp = JSONObject(response)
                    val detail = AlbumDetail(
                        albumId = resp.getInt("id"),
                        name = resp.getString("name"),
                        cover = resp.getString("cover"),
                        recordLabel = resp.getString("recordLabel"),
                        releaseDate = resp.getString("releaseDate"),
                        genre = resp.getString("genre"),
                        description = resp.getString("description")
                    )
                    //val list = mutableListOf<AlbumDetail>()
                    //for (i in 0 until resp.length()) {
                    //   val item = resp.getJSONObject(i)
                    //    list.add(i, AlbumDetail(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                    //}
                },
                Response.ErrorListener {
                    cont.resumeWithException(it)
                })
        )
    }

    fun getArtistDetail(albumId:Int, onComplete:(resp: ArtistDetail)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("musicians/$albumId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val detail = ArtistDetail(artistId = resp.getInt("id"), name = resp.getString("name"), image = resp.getString("image"), description = resp.getString("description"), birthDate = resp.getString("birthDate"))
                Log.d("Detalle", detail.toString())
                onComplete(detail)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }
    fun posttrack(albumId:Int,body: JSONObject, onComplete:(resp: JSONObject)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(postRequest("albums/$albumId/tracks",
            body,
            Response.Listener<JSONObject> { response ->
                onComplete(response)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }

    fun getCollectorDetail(collectorId:Int, onComplete:(resp: CollectorDetail)->Unit, onError: (error:VolleyError)->Unit){
        requestQueue.add(getRequest("collectors/$collectorId",
            Response.Listener<String> { response ->
                val resp = JSONObject(response)
                val detail = CollectorDetail(collectorId = resp.getInt("id"), name = resp.getString("name"), telephone = resp.getString("telephone"), email = resp.getString("email"))
                //val list = mutableListOf<AlbumDetail>()
                //for (i in 0 until resp.length()) {
                //   val item = resp.getJSONObject(i)
                //    list.add(i, AlbumDetail(albumId = item.getInt("id"),name = item.getString("name"), cover = item.getString("cover"), recordLabel = item.getString("recordLabel"), releaseDate = item.getString("releaseDate"), genre = item.getString("genre"), description = item.getString("description")))
                //}
                Log.d("Detalle Collector", detail.toString())
                onComplete(detail)
            },
            Response.ErrorListener {
                onError(it)
            }))
    }
}

