package com.shkn1marko.statrep.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

import com.shkn1marko.statrep.db.DeployStatusRepository
import com.shkn1marko.statrep.db.StatRepDatabase
import com.shkn1marko.statrep.model.DeployStatus

class StatRepViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = DeployStatusRepository(
        StatRepDatabase.getInstance(application).deployStatusDao()
    )

    private val _deployStatuses = MutableStateFlow<List<DeployStatus>>(emptyList())
    val deployStatuses: StateFlow<List<DeployStatus>> = _deployStatuses.asStateFlow()

    init {
        viewModelScope.launch {
            repository.observeAll().collect { statuses ->
                _deployStatuses.value = statuses
            }
        }
    }

    fun deleteExpired() {
        viewModelScope.launch {
            repository.deleteOlderThan()
        }
    }
}