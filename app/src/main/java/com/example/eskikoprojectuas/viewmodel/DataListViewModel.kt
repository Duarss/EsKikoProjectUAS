package com.example.eskikoprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.util.buildDb
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

// fragment data
class DataListViewModel (application: Application): AndroidViewModel(application), CoroutineScope {
    val anakLD = MutableLiveData<List<Anak>>()
    val loadingLD = MutableLiveData<Boolean>()
    val errorLD = MutableLiveData<Boolean>()

    private val job = Job()

    fun refresh() {
        loadingLD.value = true
        errorLD.value = false
        launch {
            val db = buildDb(getApplication())
            anakLD.postValue(db.anakDao().selectAll())
            loadingLD.postValue(false)
        }
    }

    fun clearTask(anak: Anak) {
        launch {
            val db = buildDb(getApplication())
            db.anakDao().deleteAnak(anak)
            anakLD.postValue(db.anakDao().selectAll())
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}

