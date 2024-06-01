package com.vaddy.filevault.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vaddy.filevault.data.model.Admin

@Dao
interface AdminDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAdmin(admin: Admin)

    @Query("SELECT * FROM admins WHERE email = :email AND password = :password")
    suspend fun authenticateAdmin(email: String, password: String): Admin?
}
