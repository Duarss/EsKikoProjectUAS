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
    fun insertAnak(vararg anak: Anak)

    @Query("SELECT * FROM anak")
    fun selectAll(): List<Anak>

    @Update
    fun updateAnak(anak:Anak)

    @Delete
    fun deleteAnak(anak:Anak)

}