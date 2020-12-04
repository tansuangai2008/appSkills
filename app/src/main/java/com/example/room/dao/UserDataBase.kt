package com.example.room.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.model.UserDTO

/**
 *  author : ly
 *  date : 2020/9/15 11:24
 *  description : 用户操作DAO
 */
@Database(entities = [UserDTO::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract fun userDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDataBase? = null
        fun getInstance(context: Context): UserDataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: (
                    Room.databaseBuilder(
                            context.applicationContext,
                            UserDataBase::class.java,
                            "user_database"
                    )//.allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build()
                    ).also {
                        INSTANCE = it
                    }
        }
    }

}