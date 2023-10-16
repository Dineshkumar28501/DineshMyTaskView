package com.sgs.mytaskview.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [LoginModel::class], version = 1, exportSchema = false)

abstract class LoginDataBase : RoomDatabase() {

    abstract fun loginDao(): LoginDao

    companion object {

        @Volatile
        private var INSTANCE: LoginDataBase? = null

        fun getDatabase(context: Context): LoginDataBase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, LoginDataBase::class.java, "login_database")
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance

            }
        }

    }

}