package com.example.eskikoprojectuas.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    var id:Int = 1, // single record only
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name="doB")
    var doB:String,
    @ColumnInfo(name="gender")
    var gender:Boolean?,
)