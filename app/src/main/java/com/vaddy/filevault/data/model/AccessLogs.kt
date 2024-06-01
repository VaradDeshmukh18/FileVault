package com.vaddy.filevault.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "access_logs")
data class AccessLog(
    @PrimaryKey(autoGenerate = true) val logId: Int = 0,
    @ColumnInfo(name = "userId") val userId: Int,
    @ColumnInfo(name = "fileId") val fileId: Int,
    @ColumnInfo(name = "accessTime") val accessTime: Long
)

