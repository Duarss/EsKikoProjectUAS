package com.example.eskikoprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.eskikoprojectuas.model.Anak
import com.example.eskikoprojectuas.model.User
import com.example.eskikoprojectuas.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class ProfileViewModel(application: Application): AndroidViewModel(application), CoroutineScope
{
    private val job = Job()

    // Two-way databinding sources
    val nameLD = MutableLiveData("")
    val doBLD = MutableLiveData("")
    val genderLD = MutableLiveData<Boolean?>(null)

    val loadingLD = MutableLiveData(false)
    val errorLD = MutableLiveData(false)

    // Single record profile (id/uuid = 1)
    private var currentUser: User? = null

    fun loadProfile() {
        loadingLD.postValue(true)
        errorLD.postValue(false)

        launch {
            try {
                val db = buildDb(getApplication())
                val dao = db.userDao()

                // Ambil user single record.
                // Kalau belum ada, buat default dulu.
                var user = dao.getUser()
                if (user == null) {
                    user = User(
                        id = 1,          // kalau kamu ubah PK jadi id=1, sesuaikan namanya
                        name = "",
                        doB = "",
                        gender = null
                    )
                    dao.upsertUser(user)
                }

                currentUser = user

                nameLD.postValue(user.name)
                doBLD.postValue(user.doB)
                genderLD.postValue(user.gender)

                loadingLD.postValue(false)
            } catch (e: Exception) {
                loadingLD.postValue(false)
                errorLD.postValue(true)
            }
        }
    }

    fun saveProfile(): Boolean {
        val n = nameLD.value?.trim().orEmpty()
        val d = doBLD.value?.trim().orEmpty()
        val g = genderLD.value

        if (n.isEmpty()) return false
        if (d.isEmpty()) return false
        if (g == null) return false

        // Pastikan currentUser ada (kalau belum, buat)
        val user = currentUser ?: User(id = 1, name = n, doB = d, gender = g).also {
            currentUser = it
        }

        user.name = n
        user.doB = d
        user.gender = g

        launch {
            val db = buildDb(getApplication())
            db.userDao().upsertUser(user)
        }

        return true
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO
}