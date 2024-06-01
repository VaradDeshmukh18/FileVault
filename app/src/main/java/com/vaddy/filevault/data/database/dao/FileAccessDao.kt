package com.vaddy.filevault.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vaddy.filevault.data.model.FileAccess

@Dao
interface FileAccessDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFileAccess(fileAccess: FileAccess)

    @Query("SELECT * FROM file_access WHERE userId = :userId AND fileId = :fileId")
    suspend fun getFileAccess(userId: Int, fileId: Int): FileAccess?

    @Query("DELETE FROM file_access WHERE userId = :userId AND fileId = :fileId")
    suspend fun revokeAccess(userId: Int, fileId: Int)
}

