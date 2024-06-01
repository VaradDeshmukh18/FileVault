package com.vaddy.filevault.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vaddy.filevault.database.AppDatabase
import com.vaddy.filevault.model.User
import com.vaddy.filevault.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
    }

    val allUsers: LiveData<List<User>> = liveData {
        emit(repository.getAllUsers())
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun authenticateUser(email: String, password: String): LiveData<User?> = liveData {
        emit(repository.authenticateUser(email, password))
    }

    fun updateUser(userId: Int, email: String, password: String) = viewModelScope.launch {
        repository.updateUser(userId, email, password)
    }

    fun getUsersByRole(role: String): LiveData<List<User>> = liveData {
        emit(repository.getUsersByRole(role))
    }
}
