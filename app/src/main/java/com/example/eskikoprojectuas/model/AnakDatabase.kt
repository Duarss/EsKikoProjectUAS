package com.example.eskikoprojectuas.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eskikoprojectuas.util.DB_NAME
import com.example.eskikoprojectuas.util.MIGRATION_1_2

@Database(entities = [Anak::class], version = 2)

abstract class AnakDatabase: RoomDatabase() {
    abstract fun anakDao(): AnakDao

    companion object {
        @Volatile private var instance : AnakDatabase ?= null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, AnakDatabase::class.java, DB_NAME
            )
                .addMigrations(MIGRATION_1_2)
                .build()

        operator fun invoke(context: Context): AnakDatabase =
            instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also { instance = it }
            }
    }
}