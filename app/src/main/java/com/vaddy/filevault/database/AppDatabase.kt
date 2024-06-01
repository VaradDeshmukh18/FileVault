package com.vaddy.filevault.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vaddy.filevault.database.dao.AccessLogDao
import com.vaddy.filevault.database.dao.FileDao
import com.vaddy.filevault.database.dao.UserDao
import com.vaddy.filevault.model.*

@Database(entities = [User::class, File::class, AccessLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun fileDao(): FileDao
    abstract fun accessLogDao(): AccessLogDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "secure-file-access-db")
                .build()
    }
}
