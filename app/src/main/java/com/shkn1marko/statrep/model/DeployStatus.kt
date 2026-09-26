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
    val cause: String? = null,
    val output: String? = null,
    val timestamp: Long
) {
    val isSuccess: Boolean
        get() = buildStatus == StageStatus.OK && deployStatus == StageStatus.OK

    companion object {
        fun fromData(data: Map<String, String>): DeployStatus? {
            val name = data["name"] ?: return null
            val buildStatus = data["buildStatus"]?.let { parseStageStatus(it) } ?: return null
            val deployStatus = data["deployStatus"]?.let { parseStageStatus(it) } ?: return null
            val timestamp = data["timestamp"]?.toLongOrNull() ?: return null

            return DeployStatus(
                name = name,
                buildStatus = buildStatus,
                deployStatus = deployStatus,
                cause = data["cause"],
                output = data["output"],
                timestamp = timestamp
            )
        }

        private fun parseStageStatus(value: String): StageStatus? =
            runCatching { enumValueOf<StageStatus>(value) }.getOrNull()
    }
}