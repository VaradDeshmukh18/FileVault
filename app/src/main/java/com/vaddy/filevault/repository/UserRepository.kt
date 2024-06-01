package com.vaddy.filevault.repository

import com.vaddy.filevault.database.dao.UserDao
import com.vaddy.filevault.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun authenticateUser(email: String, password: String) = userDao.authenticateUser(email, password)
    suspend fun getUsersByRole(role: String) = userDao.getUsersByRole(role)
    suspend fun getAllUsers() = userDao.getAllUsers()
    suspend fun updateUser(userId: Int, email: String, password: String) = userDao.updateUser(userId, email, password)
}
