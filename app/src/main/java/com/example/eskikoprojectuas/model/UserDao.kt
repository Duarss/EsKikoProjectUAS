package com.example.eskikoprojectuas.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertUser(vararg user: User)
    @Query("SELECT * FROM user WHERE id=1 LIMIT 1")
    fun getUser(): User?
}