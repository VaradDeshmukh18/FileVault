package com.vaddy.filevault.data.repository

import com.vaddy.filevault.data.database.dao.FileDao
import com.vaddy.filevault.data.model.File

class FileRepository(private val fileDao: FileDao) {
    suspend fun insertFile(file: File) = fileDao.insertFile(file)
    suspend fun getAllFiles() = fileDao.getAllFiles()
    suspend fun getFileById(fileId: Int) = fileDao.getFileById(fileId)
    suspend fun deleteFile(fileId: Int) = fileDao.deleteFile(fileId)
}
