package com.shkn1marko.statrep.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [DeployStatusEntity::class], version = 1, exportSchema = false)
@TypeConverters(StageStatusConverters::class)
abstract class StatRepDatabase : RoomDatabase() {
    abstract fun deployStatusDao(): DeployStatusDao
}