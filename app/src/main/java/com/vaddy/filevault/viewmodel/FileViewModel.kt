package com.vaddy.filevault.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.vaddy.filevault.database.AppDatabase
import com.vaddy.filevault.model.File
import com.vaddy.filevault.model.User
import com.vaddy.filevault.repository.FileRepository
import com.vaddy.filevault.repository.UserRepository
import com.vaddy.filevault.utils.AESUtil
import kotlinx.coroutines.launch
import javax.crypto.SecretKey


class FileViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: FileRepository
    private val secretKey: SecretKey = AESUtil.generateSecretKey()

    init {
        val fileDao = AppDatabase.getDatabase(application).fileDao()
        repository = FileRepository(fileDao)
    }

    val allFiles: LiveData<List<File>> = liveData {
        emit(repository.getAllFiles())
    }

    fun insertFile(file: File, fileContent: String) = viewModelScope.launch {
        val encryptedData = AESUtil.encrypt(fileContent, secretKey)
        val encryptedFile = file.copy(encryptedData = encryptedData)
        repository.insertFile(encryptedFile)
    }

    fun getFileById(fileId: Int): LiveData<File?> = liveData {
        emit(repository.getFileById(fileId))
    }

    fun decryptFile(file: File): String {
        return AESUtil.decrypt(file.encryptedData, secretKey)
    }

    fun deleteFile(fileId: Int) = viewModelScope.launch {
        repository.deleteFile(fileId)
    }
}
