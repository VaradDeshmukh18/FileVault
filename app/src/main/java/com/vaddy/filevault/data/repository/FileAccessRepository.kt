package com.vaddy.filevault.data.repository

import com.vaddy.filevault.data.database.dao.FileAccessDao
import com.vaddy.filevault.data.model.FileAccess

class FileAccessRepository(private val fileAccessDao: FileAccessDao) {
    suspend fun insertFileAccess(fileAccess: FileAccess) = fileAccessDao.insertFileAccess(fileAccess)
    suspend fun getFileAccess(userId: Int, fileId: Int) = fileAccessDao.getFileAccess(userId, fileId)
    suspend fun revokeAccess(userId: Int, fileId: Int) = fileAccessDao.revokeAccess(userId, fileId)
}
