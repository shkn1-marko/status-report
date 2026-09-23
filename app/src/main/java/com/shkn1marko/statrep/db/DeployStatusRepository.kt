package com.shkn1marko.statrep.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import com.shkn1marko.statrep.model.DeployStatus

class DeployStatusRepository(private val dao: DeployStatusDao) {

    suspend fun insert(status: DeployStatus) {
        dao.insert(status.toEntity())
    }

    suspend fun deleteOlderThan(retentionDays: Long = 5) {
        val cutoffSeconds = System.currentTimeMillis() / 1000 - retentionDays * 24 * 60 * 60
        dao.deleteOlderThan(cutoffSeconds)
    }

    fun observeAll(): Flow<List<DeployStatus>> =
        dao.getAll().map { entities -> entities.map { it.toDomain() } }
}