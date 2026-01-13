package com.example.eskikoprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class UkurViewModel(application: Application) : AndroidViewModel(application), CoroutineScope
{
    private val job = Job()

    fun addUkur(anak: Anak) {
        launch {
            val db = buildDb(getApplication())
            db.anakDao().insertAnak(anak)
        }
    }



    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}
