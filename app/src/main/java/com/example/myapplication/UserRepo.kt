package com.example.myapplication

import android.util.Log
import androidx.lifecycle.LiveData

class UserRepo(private val userDao: UserDao) {

    val allUser:LiveData<List<UserData>> = userDao.getAllUser()

    suspend fun insertUser(user: UserData){
        userDao.insertUser(user)
    }

    suspend fun updateUser(user: UserData){
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: UserData){
        userDao.deleteUser(user)
    }
}