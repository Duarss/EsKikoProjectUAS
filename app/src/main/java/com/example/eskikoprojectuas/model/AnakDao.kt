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

    @Query("SELECT * FROM anak")
    fun selectAll(): List<Anak>

    @Query("SELECT * FROM anak WHERE uuid= :id")
    fun selectAnak(id: Int): Anak

//    @Query("UPDATE anak SET weight = :weight, height = :height, usia = :usia WHERE uuid = :id")
//    fun updateAnak(weight:String, height:String, usia:String, id:Int)

    @Update
    fun updateAnak(anak:Anak)

    @Delete
    fun deleteAnak(anak:Anak)

}