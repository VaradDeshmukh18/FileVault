package com.vaddy.filevault.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "files")
data class File(
    @PrimaryKey(autoGenerate = true) val fileId: Int = 0,
    @ColumnInfo(name = "fileName") val fileName: String,
    @ColumnInfo(name = "fileSize") val fileSize: Long,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "encryptionTime") val encryptionTime: Long,
    @ColumnInfo(name = "encryptedData") val encryptedData: ByteArray
)

