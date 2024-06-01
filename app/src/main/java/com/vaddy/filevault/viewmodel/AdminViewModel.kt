package com.vaddy.filevault.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vaddy.filevault.data.database.AppDatabase
import com.vaddy.filevault.data.model.Admin
import com.vaddy.filevault.data.repository.AdminRepository
import kotlinx.coroutines.launch

class AdminViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: AdminRepository

    init {
        val adminDao = AppDatabase.getDatabase(application).adminDao()
        repository = AdminRepository(adminDao)
    }

    fun insertAdmin(admin: Admin) = viewModelScope.launch {
        repository.insertAdmin(admin)
    }

    fun authenticateAdmin(email: String, password: String): LiveData<Admin?> = liveData {
        emit(repository.authenticateAdmin(email, password))
    }
}
