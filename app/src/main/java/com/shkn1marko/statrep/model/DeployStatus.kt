package com.shkn1marko.statrep.model

enum class StageStatus {
    OK,
    FAILED,
    SKIPPED
}

data class DeployStatus(
    val name: String,
    val buildStatus: StageStatus,
    val deployStatus: StageStatus,
    val output: String? = null,
    val cause: String? = null,
    val timestamp: Long
)