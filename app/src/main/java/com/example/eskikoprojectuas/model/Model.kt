package com.example.eskikoprojectuas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anak(
    @ColumnInfo(name="weight")
    var weight:Double,
    @ColumnInfo(name="height")
    var height:Double,
    @ColumnInfo(name="usia")
    var usia:Int
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}