package com.example.eskikoprojectuas.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eskikoprojectuas.model.AnakDatabase

const val DB_NAME = "anak_db"

fun buildDb(context: Context): AnakDatabase {
    val db = AnakDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object: Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) { }
}