package com.vaddy.filevault.utils.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaddy.filevault.viewmodel.AdminViewModel

class AdminViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AdminViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AdminViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}