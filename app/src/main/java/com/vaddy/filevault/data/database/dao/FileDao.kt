package com.vaddy.filevault.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vaddy.filevault.data.model.AccessLog
import com.vaddy.filevault.data.model.File

@Dao
interface FileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFile(file: File)

    @Query("SELECT * FROM files")
    suspend fun getAllFiles(): List<File>

    @Query("SELECT * FROM files WHERE fileId = :fileId")
    suspend fun getFileById(fileId: Int): File?

    @Query("DELETE FROM files WHERE fileId = :fileId")
    suspend fun deleteFile(fileId: Int)
}

