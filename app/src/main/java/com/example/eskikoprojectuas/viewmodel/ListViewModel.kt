package com.example.eskikoprojectuas.viewmodel

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.eskikoprojectuas.databinding.FragmentUkurBinding
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.util.buildDb
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


// fragment ukur
class ListViewModel(application: Application) : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
    val todoLD = MutableLiveData<Anak>()

    fun addAnak(anak: Anak) {
        launch {
            val db = buildDb(
                getApplication()
            )
            db.anakDao().insertAll(anak)
        }
    }

    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.anakDao().selectAnak(uuid))
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO


}
