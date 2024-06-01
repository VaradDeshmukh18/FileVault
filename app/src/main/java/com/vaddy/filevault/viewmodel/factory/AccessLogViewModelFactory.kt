package com.vaddy.filevault.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.vaddy.filevault.viewmodel.AccessLogViewModel

class AccessLogViewModelFactory(
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccessLogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccessLogViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
