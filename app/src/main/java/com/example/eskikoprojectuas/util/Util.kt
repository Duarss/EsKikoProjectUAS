package com.example.eskikoprojectuas.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eskikoprojectuas.model.AppDatabase

const val DB_NAME = "app_db"

fun buildDb(context: Context): AppDatabase {
    val db = AppDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object: Migration(1,2) {
    override fun migrate(db: SupportSQLiteDatabase) {  }
}