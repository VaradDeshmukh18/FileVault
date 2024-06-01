package com.vaddy.filevault.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "file_access")
data class FileAccess(
    @PrimaryKey(autoGenerate = true) val accessId: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "fileId") val fileId: Int
)


