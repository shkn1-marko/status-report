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

    val hasDetails: Boolean
        get() = cause != null || output != null

    companion object {
        fun fromData(data: Map<String, String>): DeployStatus? {
            val name = limitLength(data["name"], 50) ?: return null
            val buildStatus = data["buildStatus"]?.let { parseStageStatus(it) } ?: return null
            val deployStatus = data["deployStatus"]?.let { parseStageStatus(it) } ?: return null
            val timestamp = data["timestamp"]?.toLongOrNull() ?: return null

            return DeployStatus(
                name = name,
                buildStatus = buildStatus,
                deployStatus = deployStatus,
                cause = limitLength(data["cause"], 500),
                output = limitLength(data["output"], 1500),
                timestamp = timestamp
            )
        }

        private fun parseStageStatus(value: String): StageStatus? =
            runCatching { enumValueOf<StageStatus>(value) }.getOrNull()

        private fun limitLength(value: String?, maxLength: Int): String? =
            value?.let { if (it.length > maxLength) it.take(maxLength) else it }
    }
}