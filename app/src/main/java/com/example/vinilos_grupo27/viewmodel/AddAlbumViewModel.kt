package com.example.vinilos_grupo27.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_grupo27.models.Album
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
        var gson = Gson()
        var jsonString = gson.toJson(album)
        val jsonAlbum = JSONObject(jsonString)
        Log.d(
            "button_6",
            "los valores en JSON ingresados son ${jsonString}"
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