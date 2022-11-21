package com.example.vinilos_grupo27.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.vinilos_grupo27.models.AlbumDetail
import com.example.vinilos_grupo27.repositories.AlbumDetailRepository

class AlbumDetailViewModel (application: Application, albumId: Int) : AndroidViewModel(application) {

    private val albumDetailRepository = AlbumDetailRepository(application)

    private val _albumDetail = MutableLiveData<AlbumDetail>()

    val albumDetail: LiveData<AlbumDetail>
        get() = _albumDetail

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
        albumDetailRepository.refreshData(id, {
            _albumDetail.postValue(it)
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
            if (modelClass.isAssignableFrom(AlbumDetailViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AlbumDetailViewModel(app, albumId) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}