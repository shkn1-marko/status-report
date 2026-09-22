package com.shkn1marko.statrep.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DeployStatusDao {

    @Insert
    suspend fun insert(entity: DeployStatusEntity)

    @Query("DELETE FROM deploy_status WHERE timestamp < :cutoffSeconds")
    suspend fun deleteOlderThan(cutoffSeconds: Long)

    @Query("SELECT * FROM deploy_status ORDER BY timestamp DESC")
    fun getAll(): Flow<List<DeployStatusEntity>>
}