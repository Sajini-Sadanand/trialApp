package com.example.imagelist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ListViewModel:ViewModel() {
    private val _querytxt = MutableLiveData<String>()
    val queryTxt:LiveData<String> get() = _querytxt

    fun setQueryText(query: String?) {
        _querytxt.value = query
    }

    private val _photos = MutableLiveData<List<Photos>>()
    val photos:LiveData<List<Photos>> get() = _photos

    init {
        _photos.value = PhotosDataSource.photosSrc
    }
}