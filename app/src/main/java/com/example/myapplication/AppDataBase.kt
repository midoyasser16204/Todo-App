package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [UserData::class], version = 2)
abstract class AppDataBase:RoomDatabase() {
    abstract fun getData():UserDao
    companion object{
        private var instance:AppDataBase?=null
        fun getDataBase (context: Context):AppDataBase{
            return instance?: synchronized(this){
                val loacalInstance= Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,"user_database"
                ).fallbackToDestructiveMigration().build()
                instance=loacalInstance
                loacalInstance
            }
        }
    }
}