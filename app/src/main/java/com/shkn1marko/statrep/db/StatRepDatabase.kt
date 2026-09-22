package com.shkn1marko.statrep.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DeployStatusEntity::class], version = 1, exportSchema = false)
@TypeConverters(StageStatusConverters::class)
abstract class StatRepDatabase : RoomDatabase() {
    abstract fun deployStatusDao(): DeployStatusDao

    companion object {
        @Volatile
        private var instance: StatRepDatabase? = null

        fun getInstance(context: Context): StatRepDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    StatRepDatabase::class.java,
                    "statrep.db"
                ).build().also { instance = it }
            }
    }
}