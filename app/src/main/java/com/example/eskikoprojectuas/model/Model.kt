package com.example.eskikoprojectuas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Anak(
    @ColumnInfo(name="weight")
    var weight:String,
    @ColumnInfo(name="height")
    var height:String,
    @ColumnInfo(name="usia")
    var usia:String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid: Int = 0
}