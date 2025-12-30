package com.example.eskikoprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// fragment data
class DataListViewModel (application: Application): AndroidViewModel(application) {
    val dataLD = MutableLiveData<ArrayList<Anak>>()
    val dataLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh() {

    }

}

