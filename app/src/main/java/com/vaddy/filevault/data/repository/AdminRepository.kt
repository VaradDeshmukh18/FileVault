package com.vaddy.filevault.data.repository

import com.vaddy.filevault.data.database.dao.AdminDao
import com.vaddy.filevault.data.model.Admin

class AdminRepository(private val adminDao: AdminDao) {
    suspend fun insertAdmin(admin: Admin) = adminDao.insertAdmin(admin)
    suspend fun authenticateAdmin(email: String, password: String) = adminDao.authenticateAdmin(email, password)
}
