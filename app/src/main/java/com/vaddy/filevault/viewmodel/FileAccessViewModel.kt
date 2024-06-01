package com.vaddy.filevault.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vaddy.filevault.data.database.AppDatabase
import com.vaddy.filevault.data.model.FileAccess
import com.vaddy.filevault.data.repository.FileAccessRepository
import kotlinx.coroutines.launch

class FileAccessViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FileAccessRepository

    init {
        val fileAccessDao = AppDatabase.getDatabase(application).fileAccessDao()
        repository = FileAccessRepository(fileAccessDao)
    }

    fun insertFileAccess(fileAccess: FileAccess) = viewModelScope.launch {
        repository.insertFileAccess(fileAccess)
    }

    fun getFileAccess(userId: Int, fileId: Int): LiveData<FileAccess?> = liveData {
        emit(repository.getFileAccess(userId, fileId))
    }

    fun revokeAccess(userId: Int, fileId: Int) = viewModelScope.launch {
        repository.revokeAccess(userId, fileId)
    }
}
