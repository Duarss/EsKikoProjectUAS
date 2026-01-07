package com.example.eskikoprojectuas.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eskikoprojectuas.util.DB_NAME
import com.example.eskikoprojectuas.util.MIGRATION_1_2

@Database(entities = [Anak::class, User::class], version = 3)

abstract class AppDatabase: RoomDatabase() {
    abstract fun anakDao(): AnakDao
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance : AppDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(
                context.applicationContext, AppDatabase::class.java, DB_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .build()

        operator fun invoke(context: Context): AppDatabase =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
    }
}