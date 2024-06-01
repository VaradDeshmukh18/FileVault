package com.vaddy.filevault.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "admins")
data class Admin(
    @PrimaryKey(autoGenerate = true) val adminId: Int = 0,
    @ColumnInfo(name = "email") val email: String,
    @ColumnInfo(name = "password") val password: String
)
