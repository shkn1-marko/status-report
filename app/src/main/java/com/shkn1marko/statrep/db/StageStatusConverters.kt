package com.shkn1marko.statrep.db

import androidx.room.TypeConverter

import com.shkn1marko.statrep.model.StageStatus

class StageStatusConverters {
    @TypeConverter
    fun fromStageStatus(value: StageStatus): String = value.name

    @TypeConverter
    fun toStageStatus(value: String): StageStatus = enumValueOf(value)
}