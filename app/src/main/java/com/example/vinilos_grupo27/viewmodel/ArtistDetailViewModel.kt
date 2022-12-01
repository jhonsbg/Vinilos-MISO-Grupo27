package com.example.vinilos_grupo27.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_grupo27.models.ArtistDetail
import com.example.vinilos_grupo27.repositories.ArtistDetailRepository

class ArtistDetailViewModel (application: Application, albumId: Int) : AndroidViewModel(application) {

    private val artistDetailRepository = ArtistDetailRepository(application)

    private val _artistDetail = MutableLiveData<ArtistDetail>()

    val artistDetail: LiveData<ArtistDetail>
        get() = _artistDetail

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = albumId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        artistDetailRepository.refreshData(id, {
            _artistDetail.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
        Log.d("ViewmoDelRefreshData", "Ok")

    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val albumId: Int) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ArtistDetailViewModel::class.java)) {

                @Suppress("UNCHECKED_CAST")
                return ArtistDetailViewModel(app, albumId) as T
                Log.d("Factory ViewModel", "Ok")
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

}