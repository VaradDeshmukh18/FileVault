package com.vaddy.filevault.utils.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaddy.filevault.viewmodel.FileAccessViewModel

class FileAccessViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FileAccessViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FileAccessViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}