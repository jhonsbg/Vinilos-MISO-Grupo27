package com.example.vinilos_grupo27.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.vinilos_grupo27.models.CollectorDetail
import com.example.vinilos_grupo27.repositories.CollectorDetailRepository

class CollectorDetailViewModel (application: Application, collectorId: Int):AndroidViewModel(application)   {
    private val collectorDetailRepository = CollectorDetailRepository(application)

    private val _collectorDetail = MutableLiveData<CollectorDetail>()

    val collectorDetail: LiveData<CollectorDetail>
        get()=_collectorDetail
    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val id:Int = collectorId

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() {
        collectorDetailRepository.refreshData(id,{
            _collectorDetail.postValue(it)
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        },{
            _eventNetworkError.value = true
        })
        Log.d("ViewmoDelRefreshData Collector", "Ok")
    }
    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    class Factory(val app: Application, val collectorId: Int): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CollectorDetailViewModel::class.java)){
                @Suppress("UNCHECKED_CAST")
                return CollectorDetailViewModel(app,collectorId) as T
            }
            throw  IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}