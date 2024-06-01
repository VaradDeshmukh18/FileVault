package com.vaddy.filevault.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vaddy.filevault.model.AccessLog
import com.vaddy.filevault.model.User

@Dao
interface AccessLogDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLog(accessLog: AccessLog)

    @Query("SELECT * FROM access_logs WHERE userId = :userId")
    suspend fun getLogsByUserId(userId: Int): List<AccessLog>

    @Query("SELECT * FROM access_logs WHERE fileId = :fileId")
    suspend fun getLogsByFileId(fileId: Int): List<AccessLog>
}
