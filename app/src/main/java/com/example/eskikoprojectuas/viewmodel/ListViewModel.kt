package com.example.eskikoprojectuas.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


// fragment ukur
class ListViewModel(application: Application) : AndroidViewModel(application) {

    // Pesan error (opsional, agar bisa tampilkan toast error saat validasi/exception)
    val errorMessageLD = MutableLiveData<String?>()

    // Flag sukses simpan. Di-observe di Fragment untuk menampilkan Toast sekali,
    // lalu direset ke false lagi agar tidak retrigger saat re-attach observer.
    val saveSuccessLD = MutableLiveData<Boolean>(false)

    fun simpanData(berat: String, tinggi: String, usia: String) {

    }

    // Dipanggil Fragment setelah Toast sukses ditampilkan, agar tidak retrigger
    fun resetSaveSuccessFlag() {
        saveSuccessLD.value = false
    }

    // Dipanggil Fragment setelah Toast error ditampilkan, agar tidak retrigger
    fun clearError() {
        errorMessageLD.value = null
    }
}
