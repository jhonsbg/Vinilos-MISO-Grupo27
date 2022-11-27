package com.example.vinilos_grupo27.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_grupo27.models.TrackNoId
import com.example.vinilos_grupo27.repositories.TrackRepository
import com.google.gson.Gson
import org.json.JSONObject

class AddTrackViewModel (application: Application, albumId: Int) : AndroidViewModel(application) {

    private val repository = TrackRepository(application)

    private val _track = MutableLiveData<TrackNoId>()

    val track: LiveData<TrackNoId>
        get() = _track

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val idAlbum:Int = albumId

    fun createTrack(track: TrackNoId){
        var gson = Gson()
        var jsonString = gson.toJson(track)
        val jsonTrack = JSONObject(jsonString)
        Log.d(
            "button_track",
            "los valores en JSON ingresados pasando por ViewModel son ${jsonString}"
        )
        postDataFromNetwork(idAlbum,jsonTrack)
    }

    private fun postDataFromNetwork(idAlbum:Int, body : JSONObject){
        repository.postData(idAlbum,body,{
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddTrackViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddTrackViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}