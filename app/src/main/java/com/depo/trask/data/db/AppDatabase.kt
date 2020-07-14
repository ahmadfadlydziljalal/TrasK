package com.depo.trask.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.depo.trask.data.db.dao.UserDao
import com.depo.trask.data.db.entities.User


@Database(
    entities =  [User::class],
    version = 1
)


abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDao() : UserDao

    companion object{

        @Volatile
        private var instance : AppDatabase? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) =
            instance ?: synchronized(LOCK){
                instance?:buildDatabase(context).also {
                    instance = it
                }
            }

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "trask"
            ).build()

    }

}