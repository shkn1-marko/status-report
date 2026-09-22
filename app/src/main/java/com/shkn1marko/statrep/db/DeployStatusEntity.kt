package com.shkn1marko.statrep.db

import androidx.room.Entity
import androidx.room.PrimaryKey

import com.shkn1marko.statrep.model.StageStatus

@Entity(tableName = "deploy_status")
data class DeployStatusEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val buildStatus: StageStatus,
    val deployStatus: StageStatus,
    val cause: String?,
    val output: String?,
    val timestamp: Long
)