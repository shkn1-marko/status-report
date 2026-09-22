package com.shkn1marko.statrep.db

import com.shkn1marko.statrep.model.DeployStatus

fun DeployStatus.toEntity(): DeployStatusEntity =
    DeployStatusEntity(
        name = name,
        buildStatus = buildStatus,
        deployStatus = deployStatus,
        cause = cause,
        output = output,
        timestamp = timestamp
    )

fun DeployStatusEntity.toDomain(): DeployStatus =
    DeployStatus(
        name = name,
        buildStatus = buildStatus,
        deployStatus = deployStatus,
        cause = cause,
        output = output,
        timestamp = timestamp
    )