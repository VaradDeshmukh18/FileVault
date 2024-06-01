package com.vaddy.filevault.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vaddy.filevault.database.AppDatabase
import com.vaddy.filevault.model.AccessLog
import com.vaddy.filevault.model.User
import com.vaddy.filevault.repository.AccessLogRepository
import com.vaddy.filevault.repository.UserRepository
import kotlinx.coroutines.launch

class AccessLogViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AccessLogRepository

    init {
        val accessLogDao = AppDatabase.getDatabase(application).accessLogDao()
        repository = AccessLogRepository(accessLogDao)
    }

    fun insertLog(accessLog: AccessLog) = viewModelScope.launch {
        repository.insertLog(accessLog)
    }

    fun getLogsByUserId(userId: Int): LiveData<List<AccessLog>> = liveData {
        emit(repository.getLogsByUserId(userId))
    }

    fun getLogsByFileId(fileId: Int): LiveData<List<AccessLog>> = liveData {
        emit(repository.getLogsByFileId(fileId))
    }
}
