package com.vaddy.filevault.data.repository

import com.vaddy.filevault.data.database.dao.AccessLogDao
import com.vaddy.filevault.data.model.AccessLog

class AccessLogRepository(private val accessLogDao: AccessLogDao) {
    suspend fun insertLog(accessLog: AccessLog) = accessLogDao.insertLog(accessLog)
    suspend fun getLogsByUserId(userId: Int) = accessLogDao.getLogsByUserId(userId)
    suspend fun getLogsByFileId(fileId: Int) = accessLogDao.getLogsByFileId(fileId)
    suspend fun getAllLogs() = accessLogDao.getAllLogs()
}
