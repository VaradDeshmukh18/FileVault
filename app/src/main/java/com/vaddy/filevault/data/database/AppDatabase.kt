package com.vaddy.filevault.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.vaddy.filevault.data.database.dao.AccessLogDao
import com.vaddy.filevault.data.database.dao.AdminDao
import com.vaddy.filevault.data.database.dao.FileAccessDao
import com.vaddy.filevault.data.database.dao.FileDao
import com.vaddy.filevault.data.database.dao.UserDao
import com.vaddy.filevault.data.model.AccessLog
import com.vaddy.filevault.data.model.Admin
import com.vaddy.filevault.data.model.File
import com.vaddy.filevault.data.model.FileAccess
import com.vaddy.filevault.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

@Database(entities = [User::class, Admin::class, File::class, AccessLog::class, FileAccess::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun adminDao(): AdminDao
    abstract fun fileDao(): FileDao
    abstract fun accessLogDao(): AccessLogDao
    abstract fun fileAccessDao(): FileAccessDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, "file-vault-db")
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        CoroutineScope(Dispatchers.IO).launch {
                            getDatabase(context).adminDao().insertAdmin(
                                Admin(email = "admin@example.com", password = "admin123")
                            )
                        }
                    }
                })
                .build()
    }
}

