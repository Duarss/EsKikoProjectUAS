package com.example.eskikoprojectuas.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AnakDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg anak: Anak)



}