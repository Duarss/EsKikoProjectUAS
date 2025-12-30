package com.example.eskikoprojectuas.util

import android.content.Context
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.eskikoprojectuas.model.AnakDatabase

val DB_NAME = "newanakdb"


fun buildDb(context: Context): AnakDatabase {
    val db = AnakDatabase.buildDatabase(context)
    return db
}

val MIGRATION_1_2 = object: Migration(1,2){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE todo ADD COLUMN " +
                "priority INTEGER DEFAULT 3 NOT NULL")
    }

}