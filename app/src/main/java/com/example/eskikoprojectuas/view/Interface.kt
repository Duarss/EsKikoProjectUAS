package com.example.eskikoprojectuas.view

import android.view.View

interface UkurListener{
    fun onClick(v: View)
}

interface ProfileListener{
    fun onSave(v: View)
    fun onPickDate(v: View)
    fun onGenderSelect(v: View)
}