package com.vaddy.filevault.data.repository

import com.vaddy.filevault.data.database.dao.UserDao
import com.vaddy.filevault.data.model.User

class UserRepository(private val userDao: UserDao) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun authenticateUser(email: String, password: String) = userDao.authenticateUser(email, password)
    suspend fun getAllUsers() = userDao.getAllUsers()
    suspend fun getUserById(userId: Int) = userDao.getUserById(userId)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
}
