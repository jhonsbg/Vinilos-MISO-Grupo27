package com.example.vinilos_grupo27.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_grupo27.models.Album
import com.example.vinilos_grupo27.models.AlbumNoId
import com.example.vinilos_grupo27.repositories.AlbumRepository
import com.google.gson.Gson
import org.json.JSONObject


class AddAlbumViewModel(application: Application): AndroidViewModel(application) {


    private val repository = AlbumRepository(application)

    private val _album = MutableLiveData<Album>()

    val album: LiveData<Album>
        get() = _album

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    fun createAlbum(album:Album){
        val albumNoId: AlbumNoId = AlbumNoId(album.name,album.cover,album.releaseDate,album.description,album.genre,album.recordLabel)
        var gson = Gson()
        var jsonString = gson.toJson(albumNoId)
        val jsonAlbum = JSONObject(jsonString)
        Log.d(
            "button_6",
            "los valores en JSON ingresados pasando por ViewModel son ${jsonString}"
        )
        postDataFromNetwork(jsonAlbum)
    }

    private fun postDataFromNetwork(body : JSONObject){
        repository.postData(body,{
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }


    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddAlbumViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddAlbumViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}