package com.vaddy.filevault.repository

import com.vaddy.filevault.database.dao.AccessLogDao
import com.vaddy.filevault.model.AccessLog

class AccessLogRepository(private val accessLogDao: AccessLogDao) {
    suspend fun insertLog(accessLog: AccessLog) = accessLogDao.insertLog(accessLog)
    suspend fun getLogsByUserId(userId: Int) = accessLogDao.getLogsByUserId(userId)
    suspend fun getLogsByFileId(fileId: Int) = accessLogDao.getLogsByFileId(fileId)
}
